package com.jkbff.ao.tyrlib.packets.server;

import java.io.DataInputStream;
import java.io.IOException;

import sk.sigp.aobot.client.types.CharacterId;

import com.jkbff.ao.tyrlib.packets.BaseServerPacket;

public class FriendRemove extends BaseServerPacket {

	public static final int TYPE = 41;
	
	private CharacterId charId;

	public FriendRemove(DataInputStream input) {
		this.charId = new CharacterId(input);
	}
	
	public FriendRemove(long charId) {
		this.charId = new CharacterId(charId);
	}
	
	public long getCharId() {
		return this.charId.getLongData();
	}
	
	public int getPacketType() {
		return FriendRemove.TYPE;
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
