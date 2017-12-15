package com.jkbff.ao.tyrlib.chat.bot

import com.jkbff.ao.tyrlib.packets.client.BaseClientPacket

trait PacketQueue {
	def getNextPacket(aoBot: AOBot, includeThrottled: Boolean): BaseClientPacket
	
	def sendPacket(packet: BaseClientPacket, aoBot: AOBot)

	def sendPacket(packet: BaseClientPacket)
	
	def registerSocket(aoBot: AOBot)
}