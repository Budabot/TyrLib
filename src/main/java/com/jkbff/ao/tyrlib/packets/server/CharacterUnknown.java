package com.jkbff.ao.tyrlib.packets.server;

import java.io.DataInputStream;
import java.io.IOException;

import sk.sigp.aobot.client.types.Int;

public class CharacterUnknown extends BaseServerPacket {

	public static final int TYPE = 10;

	protected final Int unknownInt;

	public CharacterUnknown(DataInputStream input) {
		this.unknownInt = new Int(input);
	}
	
	public CharacterUnknown(int unknownInt) {
		this.unknownInt = new Int(unknownInt);
	}
	
	public int getUnknownInt() {
		return this.unknownInt.getData();
	}
	
	public int getPacketType() {
		return CharacterUnknown.TYPE;
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
