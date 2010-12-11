package com.jkbff.ao.tyrlib.packets.client;

import java.io.DataInputStream;
import java.io.IOException;

import sk.sigp.aobot.client.types.Raw;
import sk.sigp.aobot.client.types.UserId;

import com.jkbff.ao.tyrlib.packets.BaseClientPacket;

public class BuddyAddPacket extends BaseClientPacket {

	public static final int TYPE = 40;
	public static final String NAME = "AOCP_BUDDY_ADD";
	
	private UserId userId;
	private Raw status;
	
	public BuddyAddPacket(DataInputStream input) throws IOException {
		this.userId = new UserId(input);
		this.status = new Raw(input);
	}
	
	public BuddyAddPacket(long userId, String status) {
		this.userId = new UserId(userId);
		this.status = new Raw(status);
	}
	
	public byte[] getBytes() throws IOException {
		
		return getBytes(userId, status);
	}
	
	public int getPacketType() {
		return BuddyAddPacket.TYPE;
	}
}
