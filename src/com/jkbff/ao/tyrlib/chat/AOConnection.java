package com.jkbff.ao.tyrlib.chat;

import com.jkbff.ao.tyrlib.packets.BaseClientPacket;
import com.jkbff.ao.tyrlib.packets.BaseServerPacket;

public interface AOConnection {
	public void shutdown();
	public void processPacket(BaseServerPacket packet);
	public void sendPacket(BaseClientPacket packet);
	public void setChatPacketHandler(ChatPacketHandler chatPacketHandler);
	public boolean isAlive();
	public void start();
	public Boolean isOnline(long charId);
}
