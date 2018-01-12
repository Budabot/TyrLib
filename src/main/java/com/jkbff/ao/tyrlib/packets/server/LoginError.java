package com.jkbff.ao.tyrlib.packets.server;

import java.io.DataInputStream;
import java.io.IOException;

import sk.sigp.aobot.client.types.Text;

public class LoginError extends BaseServerPacket {
	
	public static final int TYPE = 6;
	
	private Text message = null;

	public LoginError(DataInputStream input) {
		this.message = new Text(input);
	}
	
	public LoginError(String message) {
		this.message = new Text(message);
	}
	
	public String getMessage() {
		return this.message.getData();
	}

	public int getPacketType() {
		return LoginError.TYPE;
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
