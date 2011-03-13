package com.jkbff.ao.tyrlib.packets.server;

import java.io.DataInputStream;
import java.io.IOException;

import sk.sigp.aobot.client.types.Text;

import com.jkbff.ao.tyrlib.packets.BaseServerPacket;

public class LoginSeed extends BaseServerPacket {
	
	public static final int TYPE = 0;
	
	private Text seed = null;

	public LoginSeed(DataInputStream input) throws IOException {
		this.seed = new Text(input);
	}
	
	public LoginSeed(String seed) {
		this.seed = new Text(seed);
	}
	
	public String getSeed() {
		return this.seed.getStringData();
	}

	public int getPacketType() {
		return LoginSeed.TYPE;
	}
	
	public byte[] getBytes() throws IOException {
		return getBytes(seed);
	}
	
	public String toString() {
		String output = new StringBuffer()
			.append(TYPE).append(" ").append(this.getClass().getSimpleName())
			.append("\n\tSeed: ").append(seed)
			.toString();
		
		return output;
	}
}
