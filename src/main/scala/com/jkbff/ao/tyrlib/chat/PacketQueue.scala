package com.jkbff.ao.tyrlib.chat
import com.jkbff.ao.tyrlib.packets.BaseClientPacket

trait PacketQueue {
	def getNextPacket(aoBot: AOSocket, includeThrottled: Boolean): BaseClientPacket
	
	def sendPacket(packet: BaseClientPacket, aoBot: AOSocket)

	def sendPacket(packet: BaseClientPacket)
	
	def registerSocket(aoBot: AOSocket)
}