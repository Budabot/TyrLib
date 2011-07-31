package com.jkbff.ao.tyrlib.chat;

import com.jkbff.ao.tyrlib.packets.BaseServerPacket;

public interface ChatPacketHandler {
	public void processPacket(BaseServerPacket packet, AOConnection bot);
}
