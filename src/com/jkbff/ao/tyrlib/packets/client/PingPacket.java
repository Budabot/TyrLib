package com.jkbff.ao.tyrlib.packets.client;

import java.io.DataInputStream;
import java.io.IOException;

import sk.sigp.aobot.client.types.Raw;

import com.jkbff.ao.tyrlib.packets.BaseClientPacket;

/**
 * @description Client sends a ping message
 * @expects com.jkbff.ao.tyrlib.packets.server.PongPacket
 * @author Jason
 *
 */
public class PingPacket extends BaseClientPacket {

	public static final int TYPE = 100;
	public static final String NAME = "AOCP_PING";
	
	private Raw raw;
	
	public PingPacket(DataInputStream input) throws IOException {
		raw = new Raw(input);
	}
	
	public PingPacket(String raw) {
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
			.append("\n\tRaw: ").append(this.raw.getStringData())
			.toString();
	
		return output;
	}
}
