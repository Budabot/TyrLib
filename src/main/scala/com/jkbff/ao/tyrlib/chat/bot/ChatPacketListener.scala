package com.jkbff.ao.tyrlib.chat.bot

import java.io.{DataInputStream, IOException, InputStream}

import com.jkbff.ao.tyrlib.chat.socket.ServerPacketFactory
import org.apache.log4j.Logger

class ChatPacketListener(inputStream: InputStream, aoBot: AOBot) extends Thread {
	val dataInputStream: DataInputStream = new DataInputStream(inputStream)
	val log = Logger.getLogger(this.getClass())
	val serverPacketFactory = new ServerPacketFactory

	override def run() {
		while (!aoBot.shouldStop) {
			try {
				// read the packet bytes from the stream
				val packetId = dataInputStream.readUnsignedShort()
				val packetLength = dataInputStream.readUnsignedShort()
				val payload = new Array[Byte](packetLength)
				dataInputStream.readFully(payload)

				// create a packet from the bytes read in and send to the bot to process
				try {
					val packet = serverPacketFactory.createInstance(packetId, payload)
					if (packet == null) {
						log.error("Unknown packet!! packet id: '" + packetId + "'\npacketLength: '" + packetLength + "'\npayload: '" + payload + "'")
					} else {
						aoBot.processIncomingPacket(packet)
					}
				} catch {
					case e: Exception => log.error("Bot Character: '" + aoBot.character + "' packet id: '" + packetId + "'", e)
				}
			} catch {
				case e: IOException => {
					if (!aoBot.shouldStop) {
						log.error("Bot Character: '" + aoBot.character + "'", e)
						aoBot.shutdown()
					}
				}
			}
		}

		try {
			dataInputStream.close()
		} catch {
			case e: Exception => log.error("", e)
		}
	}
}