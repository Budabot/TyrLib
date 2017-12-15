package com.jkbff.ao.tyrlib.packets.client;

import java.io.DataInputStream;
import java.io.IOException;

import sk.sigp.aobot.client.types.CharacterId;

public class FriendRemove extends BaseClientPacket {

	public static final int TYPE = 41;
	
	private CharacterId charId;
	
	public FriendRemove(DataInputStream input) {
		this.charId = new CharacterId(input);
	}
	
	public FriendRemove(long charId) {
		this.charId = new CharacterId(charId);
	}
	
	public long getCharId() {
		return charId.getLongData();
	}
	
	public byte[] getBytes() throws IOException {
		return getBytes(charId);
	}
	
	public int getPacketType() {
		return FriendRemove.TYPE;
	}
	
	public String toString() {
		String output = new StringBuffer()
			.append(TYPE).append(" ").append(this.getClass().getSimpleName())
			.append("\n\tCharId: ").append(charId)
			.toString();
	
		return output;
	}
}
