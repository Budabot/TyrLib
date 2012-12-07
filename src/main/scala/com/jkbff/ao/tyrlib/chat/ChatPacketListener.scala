package com.jkbff.ao.tyrlib.chat
import java.io.DataInputStream
import java.io.IOException
import java.io.InputStream

import org.apache.log4j.Logger

import com.jkbff.ao.tyrlib.packets.BaseServerPacket

class ChatPacketListener(inputStream: InputStream, aoBot: AOSocket) extends Thread {
	val dataInputStream: DataInputStream = new DataInputStream(inputStream)
	val log = Logger.getLogger(this.getClass())

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
					val packet = BaseServerPacket.createInstance(packetId, payload)
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