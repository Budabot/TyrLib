package com.jkbff.ao.tyrlib.packets.server;

import java.io.DataInputStream;
import java.io.IOException;

import sk.sigp.aobot.client.types.Raw;
import sk.sigp.aobot.client.types.Text;
import sk.sigp.aobot.client.types.UserId;

import com.jkbff.ao.tyrlib.packets.BaseServerPacket;

public class PrivateMessagePacket extends BaseServerPacket {

	public static final int TYPE = 30;
	public static final String NAME = "MSG_PRIVATE";
	
	private UserId userId;
	private Text message;
	private Raw raw;

	public PrivateMessagePacket(DataInputStream input) throws IOException {
		this.userId = new UserId(input);
		this.message = new Text(input);
		this.raw = new Raw(input);
	}
	
	public PrivateMessagePacket(long userId, String message, String raw) {
		this.userId = new UserId(userId);
		this.message = new Text(message);
		this.raw = new Raw(raw);
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
		return PrivateMessagePacket.TYPE;
	}
	
	public byte[] getBytes() throws IOException {
		return getBytes(userId, message, raw);
	}
	
	public String toString() {
		String output = new StringBuffer()
			.append(TYPE).append(" ").append(NAME).append(" (").append(this.getClass().getName()).append(")")
			.append("\n\tUserId: ").append(userId)
			.append("\n\tMessage: ").append(message)
			.append("\n\tRaw: ").append(raw)
			.toString();
	
		return output;
	}
}
