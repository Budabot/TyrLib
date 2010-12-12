package com.jkbff.ao.tyrlib.packets.server;

import java.io.DataInputStream;
import java.io.IOException;

import sk.sigp.aobot.client.types.Raw;

import com.jkbff.ao.tyrlib.packets.BaseServerPacket;
import com.jkbff.ao.tyrlib.packets.client.PingPacket;

public class PingResponsePacket extends BaseServerPacket {

	public static final int TYPE = 100;
	public static final String NAME = "AOCP_PING";
	
	private Raw raw;
	
	public PingResponsePacket(DataInputStream input) throws IOException {
		raw = new Raw(input);
	}
	
	public PingResponsePacket(String raw) {
		this.raw = new Raw(raw);
	}
	
	public byte[] getBytes() throws IOException {
		return getBytes(raw);
	}
	
	public int getPacketType() {
		return PingPacket.TYPE;
	}
	
	public String toString() {
		String output = new StringBuffer()
			.append(TYPE).append(" ").append(NAME).append(" (").append(this.getClass().getName()).append(")")
			.append("\n\tRaw: ").append(raw)
			.toString();
	
		return output;
	}
}
