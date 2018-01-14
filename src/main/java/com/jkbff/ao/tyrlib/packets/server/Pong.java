package com.jkbff.ao.tyrlib.packets.server;

import java.io.DataInputStream;
import java.io.IOException;

import sk.sigp.aobot.client.types.Text;

public class Pong extends BaseServerPacket {

	public static final int TYPE = 100;

	protected final Text raw;
	
	public Pong(DataInputStream input) {
		raw = new Text(input);
	}
	
	public Pong(String raw) {
		this.raw = new Text(raw);
	}

	public String getText() {
		return raw.getData();
	}
	
	public byte[] getBytes() throws IOException {
		return getBytes(raw);
	}
	
	public int getPacketType() {
		return TYPE;
	}
	
	public String toString() {
		String output = new StringBuffer()
			.append(TYPE).append(" ").append(this.getClass().getSimpleName())
			.append("\n\tRaw: ").append(raw)
			.toString();
	
		return output;
	}
}
