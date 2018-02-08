package com.jkbff.ao.tyrlib.packets.client;

import java.io.DataInputStream;
import java.io.IOException;

import sk.sigp.aobot.client.types.Text;

public class CharacterLookup extends BaseClientPacket {

	public static final int TYPE = 21;

	protected final Text name;
	
	public CharacterLookup(DataInputStream input) {
		name = new Text(input);
	}
	
	public CharacterLookup(String name) {
		this.name = new Text(name);
	}
	
	public String getName() {
		return name.getData();
	}
	
	public byte[] getBytes() throws IOException {
		return getBytes(name);
	}
	
	public int getPacketType() {
		return CharacterLookup.TYPE;
	}
	
	public String toString() {
		String output = new StringBuffer()
			.append(TYPE).append(" ").append(this.getClass().getSimpleName())
			.append("\n\tName: ").append(name)
			.toString();
	
		return output;
	}
}
