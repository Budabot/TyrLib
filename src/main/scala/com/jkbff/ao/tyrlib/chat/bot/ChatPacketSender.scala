package com.jkbff.ao.tyrlib.chat.bot

import java.io.OutputStream

import org.apache.log4j.Logger

class ChatPacketSender(outputStream: OutputStream, aoBot: AOBot) extends Thread {
	val log = Logger.getLogger(this.getClass());

	override def run() {
		while (!aoBot.shouldStop) {
			try {
				val packet = aoBot.getNextPacketToSend()
				if (packet != null) {
					val bytes = packet.getBytes()
					outputStream.write(bytes)
				}
			} catch {
				case e: Exception => log.error("", e)
			}
		}

		try {
			outputStream.close()
		} catch {
			case e: Exception => log.error("", e)
		}
	}
}