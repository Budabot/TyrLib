package com.jkbff.ao.tyrlib.chat

import java.net.Socket
import scala.collection.mutable
import org.apache.log4j.Logger
import java.io.IOException
import java.net.UnknownHostException
import com.jkbff.ao.tyrlib.packets.BaseServerPacket
import aoChatLib.Crypto
import com.jkbff.ao.tyrlib.packets.BaseClientPacket
import com.jkbff.ao.tyrlib.packets.client
import com.jkbff.ao.tyrlib.packets.server

class AOSocket(chatPacketHandler: ChatPacketHandler, queue: PacketQueue, username: String, password: String, val character:String, serverAddress: String, portNumber: Int) extends Thread {
	private var chatPacketListener: ChatPacketListener = _
	private var chatPacketSender: ChatPacketSender = _
	private var socket: Socket = _
	var loginStatus = ConnectionStatus.WAITING_FOR_SEED

	val friendlist = new mutable.HashMap[Long, Friend]()

	private var lastReceivedPing = 0L
	val PING_PAYLOAD = "abcdefghijklmnopqrstuvwxyzabcdefghi"
	var pingInterval = 60000

	val log = Logger.getLogger(this.getClass())

	private var characterId = 0

	@volatile var shouldStop = false
	
	var queueTime = 0L;

	var timeBetweenPackets = 2000;
	var numBurstPackets = 3;
	
	queue.registerSocket(this)

	object ConnectionStatus extends Enumeration {
		val WAITING_FOR_SEED, WAITING_FOR_CHAR_LIST, WAITING_FOR_LOGIN_OK, LOGGED_ON = Value
	}

	override def run() {
		try {
			socket = new Socket(serverAddress, portNumber)

			chatPacketListener = new ChatPacketListener(socket.getInputStream(), this)
			chatPacketListener.setName("chatPacketListener")
			chatPacketListener.start()

			chatPacketSender = new ChatPacketSender(socket.getOutputStream(), this)
			chatPacketSender.setName("chatPacketSender")
			chatPacketSender.start()

			// send pings periodically to keep the connection alive
			lastReceivedPing = System.currentTimeMillis()
			while (!shouldStop) {
				if (loginStatus == ConnectionStatus.LOGGED_ON) {
					sendPacket(new client.Ping(PING_PAYLOAD))
				}

				synchronized {
					try {
						this.wait(pingInterval)
					} catch {
						case e: InterruptedException => log.error(e)
					}
				}

				if (System.currentTimeMillis() - lastReceivedPing > (2 * pingInterval)) {
					log.error("ping reply not received past two times")
					shutdown()
				}
			}
		} catch {
			case e: UnknownHostException => log.error("Could not connect to chat server " + serverAddress + ":" + portNumber, e)
			case e: IOException => log.error("Could not connect to chat server " + serverAddress + ":" + portNumber, e)
		} finally {
			stopAllThreads()
			chatPacketHandler.shutdownEvent()
			try {
				if (socket != null) {
					socket.close();
				}
			} catch {
				case e: IOException => log.error(e)
			}
		}
	}

	def shutdown() {
		shouldStop = true;

		// wake up from waiting to send next ping
		synchronized {
			notify();
		}
	}

	private def stopAllThreads() {
		log.info(character + " shutting down.")

		// threads have up to five seconds each to shutdown
		val start = System.currentTimeMillis()
		try {
			if (chatPacketListener != null) {
				chatPacketListener.join(5000)
			}
			if (chatPacketSender != null) {
				chatPacketSender.join(5000)
			}
		} catch {
			case e: InterruptedException => log.error("", e)
		}
		log.info("Shut down time for " + character + ": " + (System.currentTimeMillis() - start))
	}

	def processIncomingPacket(packet: BaseServerPacket) {
		log.debug("SERVER " + packet)

		// If logged on, dispatch packet to handlers, otherwise, complete login sequence
		if (loginStatus == ConnectionStatus.LOGGED_ON) {
			packet match {
				case _: server.Pong => lastReceivedPing = System.currentTimeMillis()
				case p: server.FriendUpdate => {
					friendlist.put(p.getCharId(), new Friend(p.getCharId(), if (p.getOnline() == 0) OnlineStatus.OFFLINE else OnlineStatus.ONLINE, p.getStatus()))
				}
				case _ => 
			}

			processPacket(packet)
		} else {
			processLogin(packet)
		}
	}

	def processLogin(packet: BaseServerPacket) {
		packet match {
			case loginSeed: server.LoginSeed => {
				val randomPrefix = Crypto.randomHexString(8)
				val loginString = username + "|" + loginSeed.getSeed() + "|" + password

				val key = Crypto.generateKey(randomPrefix, loginString)

				val loginRequest = new client.LoginRequest(0, username, key)
				sendPacket(loginRequest)
				loginStatus = ConnectionStatus.WAITING_FOR_CHAR_LIST
			}
			case characterListPacket: server.CharacterList => {
				val characterId = characterListPacket.getLoginUsers().find { loginUser =>
					character.equalsIgnoreCase(loginUser.getName())
				}.getOrElse(throw new RuntimeException("Could not find character with name '" + character + "' on account '" + username + "'")).getUserId()

				val selectCharacterPacket = new client.LoginSelect(characterId)
				sendPacket(selectCharacterPacket)
				loginStatus = ConnectionStatus.WAITING_FOR_LOGIN_OK
			}
			case loginOk: server.LoginOk => {
				// this is sent to dispatcher so logged in event can be handled
				processPacket(packet);
				loginStatus = ConnectionStatus.LOGGED_ON
			}
			case loginError: server.LoginError => {
				shutdown();
				throw new RuntimeException(loginError.getMessage())
			}
		}
	}

	private def processPacket(packet: BaseServerPacket) {
		chatPacketHandler.processPacket(packet, this);
	}
	
	def getNextPacketToSend(): BaseClientPacket = {
		var packet: BaseClientPacket = null
		
		// since packet is null, there are no packets to be processed
		// wait to be notified of packets to be processed
		queue.synchronized {
			do {
				val currentTime = System.currentTimeMillis()
				val waitTime = queueTime - currentTime
				val includeThrottled = if (waitTime <= 0) true else false
				packet = queue.getNextPacket(this, includeThrottled)
				if (packet == null) {
					if (waitTime > 0) {
						queue.wait(waitTime)
					} else {
						queue.wait()
					}
				} else {
					// if packet is private message or public channel message, 
					// add time to queueTime
					packet match {
						case _: client.ChannelMessage | _: client.PrivateMessageSend => {
							queueTime = math.max(currentTime - (timeBetweenPackets * numBurstPackets), queueTime)
							queueTime += timeBetweenPackets
						}
						case _ =>
					}
				}
			} while (packet == null && !shouldStop)
		}
		packet
	}

	def sendPacket(packet: BaseClientPacket) {
		log.debug("CLIENT " + packet);
		queue.sendPacket(packet, this);
	}

	def isOnline(charId: Long): OnlineStatus.Value = {
		friendlist.get(charId) match {
			case Some(friend) => friend.online
			case None => OnlineStatus.UNKNOWN
		}
	}
}