package com.jkbff.ao.tyrlib.packets.server;

import java.io.DataInputStream;
import java.io.IOException;

import sk.sigp.aobot.client.types.UserId;

import com.jkbff.ao.tyrlib.packets.BaseServerPacket;

public class BuddyRemovedPacket extends BaseServerPacket {

	public static final int TYPE = 41;
	public static final String NAME = "BUDDY_REMOVED";
	
	private UserId userId;

	public BuddyRemovedPacket(DataInputStream input) throws IOException {
		this.userId = new UserId(input);
	}
	
	public BuddyRemovedPacket(long userId) {
		this.userId = new UserId(userId);
	}
	
	public long getUserId() {
		return this.userId.getLongData();
	}
	
	public int getPacketType() {
		return BuddyRemovedPacket.TYPE;
	}
	
	public byte[] getBytes() throws IOException {
		
		return getBytes(userId);
	}
}
