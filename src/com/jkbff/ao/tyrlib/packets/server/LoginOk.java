package com.jkbff.ao.tyrlib.packets.server;

import java.io.DataInputStream;
import java.io.IOException;

import sk.sigp.aobot.client.types.AbstractType;

import com.jkbff.ao.tyrlib.packets.BaseServerPacket;

public class LoginOk extends BaseServerPacket {

	public static final int TYPE = 5;
	
	public LoginOk(DataInputStream input) {
		
	}
	
	public LoginOk() {
		
	}

	public int getPacketType() {
		return LoginOk.TYPE;
	}
	
	public byte[] getBytes() throws IOException {
		return super.getBytes((AbstractType[])null);
	}
	
	public String toString() {
		String output = new StringBuffer()
			.append(TYPE).append(" ").append(this.getClass().getSimpleName())
			.toString();
		
		return output;
	}
}
