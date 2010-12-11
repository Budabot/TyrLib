package com.jkbff.ao.tyrlib.packets.server;

import java.io.DataInputStream;
import java.io.IOException;

import sk.sigp.aobot.client.types.AbstractType;

import com.jkbff.ao.tyrlib.packets.BaseServerPacket;

public class LoginOkPacket extends BaseServerPacket {

	public static final int TYPE = 5;
	public static final String NAME = "LOGIN_OK";
	
	public LoginOkPacket(DataInputStream input) throws IOException {
		
	}
	
	public LoginOkPacket() {
		
	}

	public int getPacketType() {
		return LoginOkPacket.TYPE;
	}
	
	public byte[] getBytes() throws IOException {
		
		return super.getBytes((AbstractType[])null);
	}
	
	public String toString() {
		
		String output = new StringBuffer()
			.append(TYPE).append(" ").append(NAME).append(" (").append(this.getClass().getName()).append(")")
			.toString();
		
		return output;
	}
}
