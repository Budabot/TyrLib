package com.jkbff.ao.tyrlib.packets.server;

import java.io.DataInputStream;
import java.io.IOException;

import sk.sigp.aobot.client.types.Int;
import sk.sigp.aobot.client.types.Raw;
import sk.sigp.aobot.client.types.UserId;

import com.jkbff.ao.tyrlib.packets.BaseServerPacket;

public class BuddyAddedPacket extends BaseServerPacket {

	public static final int TYPE = 40;
	public static final String NAME = "BUDDY_ADDED";
	
	private UserId userId;
	private Int online;
	private Raw status;
	
	public BuddyAddedPacket(DataInputStream input) throws IOException {
		this.userId = new UserId(input);
		this.online = new Int(input);
		this.status = new Raw(input);
	}
	
	public BuddyAddedPacket(long userId, int online, String status) {
		this.userId = new UserId(userId);
		this.online = new Int(online);
		this.status = new Raw(status);
	}

	public long getUserId() {
		return userId.getLongData();
	}

	public int getOnline() {
		return online.getIntData();
	}

	public String getStatus() {
		return status.getStringData();
	}
	
	public int getPacketType() {
		return BuddyAddedPacket.TYPE;
	}
	
	public byte[] getBytes() throws IOException {
		
		return getBytes(userId, online, status);
	}
	
	public String toString() {
		
		String output = new StringBuffer()
			.append(TYPE).append(" ").append(NAME).append(" (").append(this.getClass().getName()).append(")")
			.append("\n\tUserId: ").append(userId.getLongData())
			.append("\n\tOnline: ").append(online.getIntData())
			.append("\n\tStatus: ").append(status.getStringData())
		.toString();
	
		return output;
	
	}
}
