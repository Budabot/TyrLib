package com.jkbff.ao.tyrlib.chat.socket

import java.io.{DataInputStream, OutputStream}
import java.net.Socket

import com.jkbff.ao.tyrlib.packets.client.BaseClientPacket
import com.jkbff.ao.tyrlib.packets.server.BaseServerPacket
import org.apache.log4j.Logger

/**
	* Sends and receives packets to and from AO Chat Server
	*
	* @param socket
	* @param serverPacketFactory
	*/
class AOClientSocket(socket: Socket, serverPacketFactory: ServerPacketFactory) {
	val outputStream: OutputStream = socket.getOutputStream()
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

		val packet = serverPacketFactory.createInstance(packetId, payload)
		if (packet == null) {
			throw new Exception("Unknown packet! packet id: '" + packetId + "'\npacketLength: '" + packetLength + "'\npayload: '" + payload + "'")
		}
		packet
	}
	
	def close(): Unit = {
		inputStream.close()
		outputStream.close()
		socket.close()
	}
}