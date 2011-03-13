package com.jkbff.ao.tyrlib.packets.server;

import java.io.DataInputStream;
import java.io.IOException;

import sk.sigp.aobot.client.types.Text;

import com.jkbff.ao.tyrlib.packets.BaseServerPacket;

public class SimpleSystemMessage extends BaseServerPacket {

	public static final int TYPE = 36;
	
	private Text message;

	public SimpleSystemMessage(DataInputStream input) {
		this.message = new Text(input);
	}
	
	public SimpleSystemMessage(String message) {
		this.message = new Text(message);
	}
	
	public String getMessage() {
		return this.message.getStringData();
	}
	
	public int getPacketType() {
		return SimpleSystemMessage.TYPE;
	}
	
	public byte[] getBytes() throws IOException {
		return getBytes(message);
	}
	
	public String toString() {
		String output = new StringBuffer()
			.append(TYPE).append(" ").append(this.getClass().getSimpleName())
			.append("\n\tMessage: ").append(message)
			.toString();
	
		return output;
	}
}
