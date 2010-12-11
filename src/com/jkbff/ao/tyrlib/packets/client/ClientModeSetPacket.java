package com.jkbff.ao.tyrlib.packets.client;

import java.io.DataInputStream;
import java.io.IOException;

import sk.sigp.aobot.client.types.Int;

import com.jkbff.ao.tyrlib.packets.BaseClientPacket;

public class ClientModeSetPacket extends BaseClientPacket {

	public static final int TYPE = 71;
	public static final String NAME = "AOCP_CLIENT_MODE_SET";
	
	private Int unknownInt1;
	private Int unknownInt2;
	private Int unknownInt3;
	private Int unknownInt4;
	
	public ClientModeSetPacket(DataInputStream input) throws IOException {
		unknownInt1 = new Int(input);
		unknownInt2 = new Int(input);
		unknownInt3 = new Int(input);
		unknownInt4 = new Int(input);
	}
	
	public ClientModeSetPacket(int unknownInt1, int unknownInt2, int unknownInt3, int unknownInt4) {
		this.unknownInt1 = new Int(unknownInt1);
		this.unknownInt2 = new Int(unknownInt2);
		this.unknownInt3 = new Int(unknownInt3);
		this.unknownInt4 = new Int(unknownInt4);
	}
	
	public byte[] getBytes() throws IOException {
		
		return getBytes(unknownInt1, unknownInt2, unknownInt3, unknownInt4);
	}
	
	public int getPacketType() {
		return ClientModeSetPacket.TYPE;
	}
}
