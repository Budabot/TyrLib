package com.jkbff.ao.tyrlib.packets.server;

import java.io.DataInputStream;
import java.io.IOException;

import sk.sigp.aobot.client.types.UserId;

import com.jkbff.ao.tyrlib.packets.BaseServerPacket;

public class PrivateGroupKickedPacket extends BaseServerPacket {

	public static final int TYPE = 51;
	public static final String NAME = "PRIVGRP_KICKED";
	
	private UserId userId;

	public PrivateGroupKickedPacket(DataInputStream input) throws IOException {
		this.userId = new UserId(input);
	}
	
	public PrivateGroupKickedPacket(long userId) {
		this.userId = new UserId(userId);
	}
	
	public long getUserId() {
		return this.userId.getLongData();
	}
	
	public int getPacketType() {
		return PrivateGroupKickedPacket.TYPE;
	}
	
	public byte[] getBytes() throws IOException {
		return getBytes(userId);
	}
	
	public String toString() {
		String output = new StringBuffer()
			.append(TYPE).append(" ").append(NAME).append(" (").append(this.getClass().getName()).append(")")
			.append("\n\tUserId: ").append(userId)
			.toString();
	
		return output;
	}
}
