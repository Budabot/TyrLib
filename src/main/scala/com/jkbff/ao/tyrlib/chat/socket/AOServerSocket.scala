package com.jkbff.ao.tyrlib.chat.socket

import java.io.{DataInputStream, OutputStream}
import java.net.Socket

import com.jkbff.ao.tyrlib.packets.client.BaseClientPacket
import com.jkbff.ao.tyrlib.packets.server.BaseServerPacket

/**
	* Sends and receives packets as if AO Chat Server
	*
	* @param socket
	* @param clientPacketFactory
	*/
class AOServerSocket(socket: Socket, clientPacketFactory: ClientPacketFactory) {
	val outputStream: OutputStream = socket.getOutputStream()
	val inputStream: DataInputStream = new DataInputStream(socket.getInputStream())

	def sendPacket(packet: BaseServerPacket) {
		outputStream.write(packet.getBytes)
	}
	
	// this blocks until data is available
	def readPacket(): BaseClientPacket = {
		// read the packet bytes from the stream
		val packetId = inputStream.readUnsignedShort()
		val packetLength = inputStream.readUnsignedShort()
		val payload = new Array[Byte](packetLength)
		inputStream.readFully(payload)

		// create a packet from the bytes read in and send to the bot to process
		val packet = clientPacketFactory.createInstance(packetId, payload)
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