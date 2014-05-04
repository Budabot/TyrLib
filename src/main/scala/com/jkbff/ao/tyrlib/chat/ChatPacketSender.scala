package com.jkbff.ao.tyrlib.chat
import java.io.OutputStream
import org.apache.log4j.Logger
import java.util.concurrent.LinkedBlockingQueue
import com.jkbff.ao.tyrlib.packets.BaseClientPacket
import com.jkbff.ao.tyrlib.packets.client.PrivateMessageSend
import com.jkbff.ao.tyrlib.packets.client.ChannelMessage

class ChatPacketSender(outputStream: OutputStream, aoBot: AOSocket) extends Thread {
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