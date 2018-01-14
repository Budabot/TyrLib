package com.jkbff.ao.tyrlib.packets.server;

import java.io.DataInputStream;
import java.io.IOException;

import sk.sigp.aobot.client.types.CharacterId;

public class PrivateChannelCharacterLeave extends BaseServerPacket {

	public static final int TYPE = 56;

	protected final CharacterId charId1;
	protected final CharacterId charId2;

	public PrivateChannelCharacterLeave(DataInputStream input) {
		this.charId1 = new CharacterId(input);
		this.charId2 = new CharacterId(input);
	}
	
	public PrivateChannelCharacterLeave(long charId1, long charId2) {
		this.charId1 = new CharacterId(charId1);
		this.charId2 = new CharacterId(charId2);
	}
	
	public long getCharId1() {
		return this.charId1.getData();
	}
	
	public long getCharId2() {
		return this.charId2.getData();
	}
	
	public int getPacketType() {
		return PrivateChannelCharacterLeave.TYPE;
	}
	
	public byte[] getBytes() throws IOException {
		return getBytes(charId1, charId2);
	}
	
	public String toString() {
		String output = new StringBuffer()
			.append(TYPE).append(" ").append(this.getClass().getSimpleName())
			.append("\n\tCharId1: ").append(charId1)
			.append("\n\tCharId2: ").append(charId2)
			.toString();
	
		return output;
	}
}
