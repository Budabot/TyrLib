package com.jkbff.ao.tyrlib.packets.server;

import java.io.DataInputStream;
import java.io.IOException;

import sk.sigp.aobot.client.types.CharacterId;

public class PrivateChannelInvite extends BaseServerPacket {

	public static final int TYPE = 50;
	
	private CharacterId charId;

	public PrivateChannelInvite(DataInputStream input) {
		this.charId = new CharacterId(input);
	}
	
	public PrivateChannelInvite(long charId) {
		this.charId = new CharacterId(charId);
	}
	
	public long getCharId() {
		return this.charId.getLongData();
	}
	
	public int getPacketType() {
		return PrivateChannelInvite.TYPE;
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
