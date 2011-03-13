package com.jkbff.ao.tyrlib.packets.server;

import java.io.DataInputStream;
import java.io.IOException;

import sk.sigp.aobot.client.types.Int;

import com.jkbff.ao.tyrlib.packets.BaseServerPacket;

public class ClientUnknownPacket extends BaseServerPacket {

	public static final int TYPE = 10;
	
	private Int unknownInt;

	public ClientUnknownPacket(DataInputStream input) {
		this.unknownInt = new Int(input);
	}
	
	public ClientUnknownPacket(int unknownInt) {
		this.unknownInt = new Int(unknownInt);
	}
	
	public int getUnknownInt() {
		return this.unknownInt.getIntData();
	}
	
	public int getPacketType() {
		return ClientUnknownPacket.TYPE;
	}
	
	public byte[] getBytes() throws IOException {
		return getBytes(unknownInt);
	}
	
	public String toString() {
		String output = new StringBuffer()
			.append(TYPE).append(" ").append(this.getClass().getSimpleName())
			.append("\n\tUnknownInt: ").append(unknownInt)
			.toString();
	
		return output;
	}
}
