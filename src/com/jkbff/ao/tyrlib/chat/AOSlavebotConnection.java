package com.jkbff.ao.tyrlib.chat;

import java.util.ArrayList;
import java.util.List;

import com.jkbff.ao.tyrlib.packets.BaseClientPacket;
import com.jkbff.ao.tyrlib.packets.BaseServerPacket;
import com.jkbff.ao.tyrlib.packets.client.FriendRemove;
import com.jkbff.ao.tyrlib.packets.client.FriendUpdate;

public class AOSlavebotConnection implements AOConnection {
	
	private List<AOSingleConnection> conns = new ArrayList<AOSingleConnection>();
	private ChatPacketHandler chatPacketHandler;
	
	@Override
	public void processPacket(BaseServerPacket packet) {
		chatPacketHandler.processPacket(packet, conns.get(0));
	}
	
	@Override
	public void sendPacket(BaseClientPacket packet) {
		conns.get(0).sendPacket(packet);
	}

	public void sendPacket(FriendUpdate packet) {
		int numBuddies = 0;
		AOSingleConnection leastBuddies = null;
		for (AOSingleConnection conn : conns) {
			if (leastBuddies == null || conn.getFriendlist().size() < numBuddies) {
				leastBuddies = conn;
				numBuddies = conn.getFriendlist().size();
			}
		}
		leastBuddies.sendPacket(packet);
	}
	
	public void sendPacket(FriendRemove packet) {
		for (AOSingleConnection conn : conns) {
			if (conn.getFriendlist().containsKey(packet.getUserId())) {
				conn.sendPacket(packet);
			}
		}
	}
	
	@Override
	public Boolean isOnline(long charId) {
		Boolean isOnline = null;
		for (AOSingleConnection conn : conns) {
			isOnline = conn.isOnline(charId);
			if (isOnline != null) {
				return isOnline;
			}
		}
		return isOnline;
    }

	@Override
	public void setChatPacketHandler(ChatPacketHandler chatPacketHandler) {
		this.chatPacketHandler = chatPacketHandler;
	}
	
	@Override
	public void start() {
		for (AOConnection conn : conns) {
			conn.start();
		}
	}

	@Override
	public void shutdown() {
		for (AOConnection conn : conns) {
			conn.shutdown();
		}
	}
	
	@Override
	public boolean isAlive() {
		for (AOConnection conn : conns) {
			if (!conn.isAlive()) {
				return false;
			}
		}
		return true;
	}
	
	public void setAOSingleConnections(List<AOSingleConnection> conns) {
		this.conns = conns;
	}
}
