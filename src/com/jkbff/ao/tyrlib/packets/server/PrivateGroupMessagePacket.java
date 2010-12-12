package com.jkbff.ao.tyrlib.packets.server;

import java.io.DataInputStream;
import java.io.IOException;

import sk.sigp.aobot.client.types.Raw;
import sk.sigp.aobot.client.types.Text;
import sk.sigp.aobot.client.types.UserId;

import com.jkbff.ao.tyrlib.packets.BaseServerPacket;

public class PrivateGroupMessagePacket extends BaseServerPacket {

	public static final int TYPE = 57;
	public static final String NAME = "PRIVGRP_MESSAGE";
	
	private UserId userId1;
	private UserId userId2;
	private Text message;
	private Raw raw;

	public PrivateGroupMessagePacket(DataInputStream input) throws IOException {
		this.userId1 = new UserId(input);
		this.userId2 = new UserId(input);
		this.message = new Text(input);
		this.raw = new Raw(input);
	}
	
	public PrivateGroupMessagePacket(long userId1, long userId2, String message, String raw) {
		this.userId1 = new UserId(userId1);
		this.userId2 = new UserId(userId2);
		this.message = new Text(message);
		this.raw = new Raw(raw);
	}
	
	public long getUserId1() {
		return this.userId1.getLongData();
	}
	
	public long getUserId2() {
		return this.userId2.getLongData();
	}
	
	public String getMessage() {
		return this.message.getStringData();
	}
	
	public String getRaw() {
		return this.raw.getStringData();
	}
	
	public int getPacketType() {
		return PrivateGroupMessagePacket.TYPE;
	}
	
	public byte[] getBytes() throws IOException {
		return getBytes(userId1, userId2, message, raw);
	}
	
	public String toString() {
		String output = new StringBuffer()
			.append(TYPE).append(" ").append(NAME).append(" (").append(this.getClass().getName()).append(")")
			.append("\n\tUserId1: ").append(userId1)
			.append("\n\tUserId2: ").append(userId2)
			.append("\n\tMessage: ").append(message)
			.append("\n\tRaw: ").append(raw)
			.toString();
	
		return output;
	}
}
