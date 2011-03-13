package com.jkbff.ao.tyrlib.packets.server;

import java.io.DataInputStream;
import java.io.IOException;

import sk.sigp.aobot.client.types.Raw;

import com.jkbff.ao.tyrlib.packets.BaseServerPacket;

public class Ping extends BaseServerPacket {

	public static final int TYPE = 100;
	
	private Raw raw;
	
	public Ping(DataInputStream input) throws IOException {
		raw = new Raw(input);
	}
	
	public Ping(String raw) {
		this.raw = new Raw(raw);
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
