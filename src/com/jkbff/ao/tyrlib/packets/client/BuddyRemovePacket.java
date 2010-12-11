package com.jkbff.ao.tyrlib.packets.client;

import java.io.DataInputStream;
import java.io.IOException;

import sk.sigp.aobot.client.types.UserId;

import com.jkbff.ao.tyrlib.packets.BaseClientPacket;

public class BuddyRemovePacket extends BaseClientPacket {

	public static final int TYPE = 41;
	public static final String NAME = "AOCP_BUDDY_REMOVE";
	
	private UserId userId;
	
	public BuddyRemovePacket(DataInputStream input) throws IOException {
		this.userId = new UserId(input);
	}
	
	public BuddyRemovePacket(long userId) {
		this.userId = new UserId(userId);
	}
	
	public byte[] getBytes() throws IOException {
		
		return getBytes(userId);
	}
	
	public int getPacketType() {
		return BuddyRemovePacket.TYPE;
	}
}
