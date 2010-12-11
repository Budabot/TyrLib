package com.jkbff.ao.tyrlib.packets.server;

import java.io.DataInputStream;
import java.io.IOException;

import sk.sigp.aobot.client.types.Text;

import com.jkbff.ao.tyrlib.packets.BaseServerPacket;

public class LoginErrorPacket extends BaseServerPacket {
	
	public static final int TYPE = 6;
	public static final String NAME = "LOGIN_ERROR";
	
	private Text message = null;

	public LoginErrorPacket(DataInputStream input) throws IOException {
		this.message = new Text(input);
	}
	
	public LoginErrorPacket(String message) {
		this.message = new Text(message);
	}
	
	public String getMessage() {
		return this.message.getStringData();
	}

	public int getPacketType() {
		return LoginErrorPacket.TYPE;
	}
	
	public byte[] getBytes() throws IOException {
		
		return getBytes(message);
	}
}
