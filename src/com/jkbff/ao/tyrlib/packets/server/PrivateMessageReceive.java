package com.jkbff.ao.tyrlib.packets.server;

import java.io.DataInputStream;
import java.io.IOException;

import sk.sigp.aobot.client.types.Text;
import sk.sigp.aobot.client.types.UserId;

import com.jkbff.ao.tyrlib.packets.BaseServerPacket;

public class PrivateMessageReceive extends BaseServerPacket {

	public static final int TYPE = 30;
	
	private UserId userId;
	private Text message;
	private Text raw;

	public PrivateMessageReceive(DataInputStream input) {
		this.userId = new UserId(input);
		this.message = new Text(input);
		this.raw = new Text(input);
	}
	
	public PrivateMessageReceive(long userId, String message, String raw) {
		this.userId = new UserId(userId);
		this.message = new Text(message);
		this.raw = new Text(raw);
	}
	
	public long getUserId() {
		return this.userId.getLongData();
	}
	
	public String getMessage() {
		return this.message.getStringData();
	}
	
	public String getRaw() {
		return this.raw.getStringData();
	}

	public int getPacketType() {
		return PrivateMessageReceive.TYPE;
	}
	
	public byte[] getBytes() throws IOException {
		return getBytes(userId, message, raw);
	}
	
	public String toString() {
		String output = new StringBuffer()
			.append(TYPE).append(" ").append(this.getClass().getSimpleName())
			.append("\n\tUserId: ").append(userId)
			.append("\n\tMessage: ").append(message)
			.append("\n\tRaw: ").append(raw)
			.toString();
	
		return output;
	}
}
