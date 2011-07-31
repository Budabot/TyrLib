package com.jkbff.ao.tyrlib.packets.client;

import java.io.DataInputStream;
import java.io.IOException;

import sk.sigp.aobot.client.types.Text;

import com.jkbff.ao.tyrlib.packets.BaseClientPacket;

/**
 * @description Client sends a ping message
 * @expects com.jkbff.ao.tyrlib.packets.server.PongPacket
 * @author Jason
 *
 */
public class Ping extends BaseClientPacket {

	public static final int TYPE = 100;
	
	private Text raw;
	
	public Ping(DataInputStream input) {
		raw = new Text(input);
	}
	
	public Ping(String raw) {
		this.raw = new Text(raw);
	}
	
	public byte[] getBytes() throws IOException {
		return getBytes(raw);
	}
	
	public int getPacketType() {
		return Ping.TYPE;
	}
	
	public String toString() {
		String output = new StringBuffer()
			.append(TYPE).append(" ").append(this.getClass().getSimpleName())
			.append("\n\tRaw: ").append(raw)
			.toString();
	
		return output;
	}
}
