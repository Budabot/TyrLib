package com.jkbff.ao.tyrlib.chat

import java.util.ArrayDeque
import org.apache.log4j.Logger
import com.jkbff.ao.tyrlib.packets.client
import com.jkbff.ao.tyrlib.packets.BaseClientPacket

class ThrottledPacketQueue extends PacketQueue {
	val packetQueue = new ArrayDeque[BaseClientPacket]()
	val throttledPacketQueue = new ArrayDeque[BaseClientPacket]()
	var specificBotQueue = Map[AOSocket, ArrayDeque[BaseClientPacket]]()
	val log = Logger.getLogger(this.getClass())
	
	def getNextPacket(aoBot: AOSocket, includeThrottled: Boolean): BaseClientPacket = {
		synchronized {
			if (includeThrottled && throttledPacketQueue.peek() != null) {
				throttledPacketQueue.poll()
			} else if (!specificBotQueue(aoBot).isEmpty()) {
				specificBotQueue(aoBot).poll()
			} else {
				packetQueue.poll()
			}
		}
	}
	
	def sendPacket(packet: BaseClientPacket, aoBot: AOSocket) {
		synchronized {
			specificBotQueue(aoBot).add(packet)
			notifyAll()
		}
	}

	def sendPacket(packet: BaseClientPacket) {
		synchronized {
			packet match {
				case p: client.FriendUpdate => {
					val bot = findBotWithFewestFriends() 
					bot.friendlist.put(p.getCharId(), new Friend(p.getCharId(), OnlineStatus.UNKNOWN, p.getStatus()))
					sendPacket(packet, bot)
				}
				case p: client.FriendRemove => {
					val bot = findBotWithBuddy(p.getCharId())
					sendPacket(packet, bot)
				}
				case _: client.ChannelMessage | _: client.PrivateMessageSend => {
					throttledPacketQueue.add(packet)
					notify()
				}
				case _ => {
					packetQueue.add(packet)
					notify()
				}
			}
		}
	}
	
	def registerSocket(aoBot: AOSocket) {
		specificBotQueue += ((aoBot, new ArrayDeque[BaseClientPacket]()))
	}
	
	def findBotWithFewestFriends(): AOSocket = {
		specificBotQueue.reduce( (bot1, bot2) => if (bot1._1.friendlist.size > bot2._1.friendlist.size) bot2 else bot1)._1
	}
	
	def findBotWithBuddy(charId: Long): AOSocket = {
		specificBotQueue.find(x => x._1.friendlist.contains(charId)).get._1
	}
	
	def getSockets(): Iterable[AOSocket] = {
		specificBotQueue.keys
	}
}