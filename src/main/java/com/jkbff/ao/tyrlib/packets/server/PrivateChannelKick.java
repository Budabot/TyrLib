package com.jkbff.ao.tyrlib.packets.server;

import java.io.DataInputStream;
import java.io.IOException;

import sk.sigp.aobot.client.types.CharacterId;

public class PrivateChannelKick extends BaseServerPacket {

	public static final int TYPE = 51;
	
	private CharacterId charId;

	public PrivateChannelKick(DataInputStream input) {
		this.charId = new CharacterId(input);
	}
	
	public PrivateChannelKick(long charId) {
		this.charId = new CharacterId(charId);
	}
	
	public long getCharId() {
		return this.charId.getLongData();
	}
	
	public int getPacketType() {
		return PrivateChannelKick.TYPE;
	}
	
	public byte[] getBytes() throws IOException {
		return getBytes(charId);
	}
	
	public String toString() {
		String output = new StringBuffer()
			.append(TYPE).append(" ").append(this.getClass().getSimpleName())
			.append("\n\tCharId: ").append(charId)
			.toString();
	
		return output;
	}
}
