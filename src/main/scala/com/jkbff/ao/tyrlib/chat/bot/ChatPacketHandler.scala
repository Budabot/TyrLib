package com.jkbff.ao.tyrlib.chat.bot

import com.jkbff.ao.tyrlib.packets.server.BaseServerPacket

trait ChatPacketHandler {
  def processPacket(packet: BaseServerPacket, bot: AOBot): Unit
  def shutdownEvent(): Unit
}
