package com.jkbff.ao.tyrlib.chat

import java.net.Socket
import org.apache.log4j.Logger
import com.jkbff.ao.tyrlib.packets.BaseServerPacket
import com.jkbff.ao.tyrlib.packets.BaseClientPacket
import java.io.DataInputStream

class AOClientSocket(socket: Socket) {
	val outputStream = socket.getOutputStream()
	val inputStream = new DataInputStream(socket.getInputStream())

	val log = Logger.getLogger(this.getClass())
	
	def sendPacket(packet: BaseClientPacket) {
		outputStream.write(packet.getBytes)
	}
	
	// this blocks until data is available
	def readPacket(): BaseServerPacket = {
		// read the packet bytes from the stream
		val packetId = inputStream.readUnsignedShort()
		val packetLength = inputStream.readUnsignedShort()
		val payload = new Array[Byte](packetLength)
		inputStream.readFully(payload)

		// create a packet from the bytes read in and send to the bot to process
		val packet = BaseServerPacket.createInstance(packetId, payload)
		if (packet == null) {
			throw new Exception("Unknown packet! packet id: '" + packetId + "'\npacketLength: '" + packetLength + "'\npayload: '" + payload + "'")
		}
		packet
	}
}