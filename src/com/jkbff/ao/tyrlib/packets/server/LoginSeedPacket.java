package com.jkbff.ao.tyrlib.packets.server;

import java.io.DataInputStream;
import java.io.IOException;

import sk.sigp.aobot.client.types.Text;

import com.jkbff.ao.tyrlib.packets.BaseServerPacket;

public class LoginSeedPacket extends BaseServerPacket {
	
	public static final int TYPE = 0;
	public static final String NAME = "LOGIN_SEED";
	
	private Text seed = null;

	public LoginSeedPacket(DataInputStream input) throws IOException {
		this.seed = new Text(input);
	}
	
	public LoginSeedPacket(String seed) {
		this.seed = new Text(seed);
	}
	
	public String getSeed() {
		return this.seed.getStringData();
	}

	public int getPacketType() {
		return LoginSeedPacket.TYPE;
	}
	
	public byte[] getBytes() throws IOException {
		
		return getBytes(seed);
	}
	
	public String toString() {
		
		String output = new StringBuffer()
			.append(TYPE).append(" ").append(NAME).append(" (").append(this.getClass().getName()).append(")")
			.append("\n\tSeed: ").append(this.seed.getStringData())
			.toString();
		
		return output;
	}
}
