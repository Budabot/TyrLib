package com.jkbff.ao.tyrlib.packets.server;

import java.io.DataInputStream;
import java.io.IOException;

import sk.sigp.aobot.client.types.Text;

public class BroadcastMessage extends BaseServerPacket {

	public static final int TYPE = 35;
	
	private Text text;
	private Text message;
	private Text raw;

	public BroadcastMessage(DataInputStream input) {
		this.text = new Text(input);
		this.message = new Text(input);
		this.raw = new Text(input);
	}
	
	public BroadcastMessage(String text, String message, String raw) {
		this.text = new Text(text);
		this.message = new Text(message);
		this.raw = new Text(raw);
	}
	
	public String getText() {
		return this.text.getData();
	}
	
	public String getMessage() {
		return this.message.getData();
	}
	
	public String getRaw() {
		return this.raw.getData();
	}
	
	public int getPacketType() {
		return BroadcastMessage.TYPE;
	}
	
	public byte[] getBytes() throws IOException {
		return getBytes(text, message, raw);
	}
	
	public String toString() {
		String output = new StringBuffer()
			.append(TYPE).append(" ").append(this.getClass().getSimpleName())
			.append("\n\tText: '").append(text).append("'")
			.append("\n\tMessage: '").append(message).append("'")
			.append("\n\tRaw: ").append(raw)
			.toString();
	
		return output;
	}
}
