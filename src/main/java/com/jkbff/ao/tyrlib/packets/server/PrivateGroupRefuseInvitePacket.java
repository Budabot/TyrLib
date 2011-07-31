package com.jkbff.ao.tyrlib.packets.server;

import java.io.DataInputStream;
import java.io.IOException;

import sk.sigp.aobot.client.types.CharacterId;

import com.jkbff.ao.tyrlib.packets.BaseServerPacket;

public class PrivateGroupRefuseInvitePacket extends BaseServerPacket {

	public static final int TYPE = 58;
	
	private CharacterId charId1;
	private CharacterId charId2;

	public PrivateGroupRefuseInvitePacket(DataInputStream input) {
		this.charId1 = new CharacterId(input);
		this.charId2 = new CharacterId(input);
	}
	
	public PrivateGroupRefuseInvitePacket(long charId1, long charId2) {
		this.charId1 = new CharacterId(charId1);
		this.charId2 = new CharacterId(charId2);
	}
	
	public long getCharId1() {
		return this.charId1.getLongData();
	}
	
	public long getUserId2() {
		return this.charId2.getLongData();
	}
	
	public int getPacketType() {
		return PrivateGroupRefuseInvitePacket.TYPE;
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
