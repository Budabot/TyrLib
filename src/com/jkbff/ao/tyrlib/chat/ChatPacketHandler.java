package com.jkbff.ao.tyrlib.chat;

import com.jkbff.ao.tyrlib.packets.BaseServerPacket;

public interface ChatPacketHandler {
	public <T extends BaseServerPacket> void processPacket(T packet, AOConnection bot);
}
