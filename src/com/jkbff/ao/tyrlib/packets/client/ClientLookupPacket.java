package com.jkbff.ao.tyrlib.packets.client;

import java.io.DataInputStream;
import java.io.IOException;

import sk.sigp.aobot.client.types.Text;

import com.jkbff.ao.tyrlib.packets.BaseClientPacket;

public class ClientLookupPacket extends BaseClientPacket {

	public static final int TYPE = 21;
	public static final String NAME = "AOCP_CLIENT_LOOKUP";
	
	private Text name;
	
	public ClientLookupPacket(DataInputStream input) throws IOException {
		name = new Text(input);
	}
	
	public ClientLookupPacket(String name) {
		this.name = new Text(name);
	}
	
	public byte[] getBytes() throws IOException {
		
		return getBytes(name);
	}
	
	public int getPacketType() {
		return ClientLookupPacket.TYPE;
	}
}
