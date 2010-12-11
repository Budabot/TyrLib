package com.jkbff.ao.tyrlib.packets.server;

import java.io.DataInputStream;
import java.io.IOException;

import sk.sigp.aobot.client.types.Text;

import com.jkbff.ao.tyrlib.packets.BaseServerPacket;

public class SystemMessagePacket extends BaseServerPacket {

	public static final int TYPE = 36;
	public static final String NAME = "MSG_SYSTEM";
	
	private Text message;

	public SystemMessagePacket(DataInputStream input) throws IOException {
		this.message = new Text(input);
	}
	
	public SystemMessagePacket(String message) {
		this.message = new Text(message);
	}
	
	public String getMessage() {
		return this.message.getStringData();
	}
	
	public int getPacketType() {
		return SystemMessagePacket.TYPE;
	}
	
	public byte[] getBytes() throws IOException {
		
		return getBytes(message);
	}
}
