package com.jkbff.ao.tyrlib.packets.client;

import java.io.DataInputStream;
import java.io.IOException;

import sk.sigp.aobot.client.types.Text;

import com.jkbff.ao.tyrlib.packets.BaseClientPacket;

public class CharacterRequest extends BaseClientPacket {

	public static final int TYPE = 21;
	
	private Text name;
	
	public CharacterRequest(DataInputStream input) {
		name = new Text(input);
	}
	
	public CharacterRequest(String name) {
		this.name = new Text(name);
	}
	
	public String getName() {
		return name.getStringData();
	}
	
	public byte[] getBytes() throws IOException {
		return getBytes(name);
	}
	
	public int getPacketType() {
		return CharacterRequest.TYPE;
	}
	
	public String toString() {
		String output = new StringBuffer()
			.append(TYPE).append(" ").append(this.getClass().getSimpleName())
			.append("\n\tName: ").append(name)
			.toString();
	
		return output;
	}
}
