package com.jkbff.ao.tyrlib.packets.server;

import java.io.DataInputStream;
import java.io.IOException;

import sk.sigp.aobot.client.types.Text;
import sk.sigp.aobot.client.types.CharacterId;

public class PrivateMessageReceive extends BaseServerPacket {

	public static final int TYPE = 30;
	
	private CharacterId charId;
	private Text message;
	private Text raw;

	public PrivateMessageReceive(DataInputStream input) {
		this.charId = new CharacterId(input);
		this.message = new Text(input);
		this.raw = new Text(input);
	}
	
	public PrivateMessageReceive(long charId, String message, String raw) {
		this.charId = new CharacterId(charId);
		this.message = new Text(message);
		this.raw = new Text(raw);
	}
	
	public long getCharId() {
		return this.charId.getData();
	}
	
	public String getMessage() {
		return this.message.getData();
	}
	
	public String getRaw() {
		return this.raw.getData();
	}

	public int getPacketType() {
		return PrivateMessageReceive.TYPE;
	}
	
	public byte[] getBytes() throws IOException {
		return getBytes(charId, message, raw);
	}
	
	public String toString() {
		String output = new StringBuffer()
			.append(TYPE).append(" ").append(this.getClass().getSimpleName())
			.append("\n\tCharId: ").append(charId)
			.append("\n\tMessage: ").append(message)
			.append("\n\tRaw: ").append(raw)
			.toString();
	
		return output;
	}
}
