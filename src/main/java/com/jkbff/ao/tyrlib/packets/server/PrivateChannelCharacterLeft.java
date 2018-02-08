package com.jkbff.ao.tyrlib.packets.server;

import java.io.DataInputStream;
import java.io.IOException;

import sk.sigp.aobot.client.types.CharacterId;

public class PrivateChannelCharacterLeft extends BaseServerPacket {

	public static final int TYPE = 56;

	protected final CharacterId privateChannelId;
	protected final CharacterId charId;

	public PrivateChannelCharacterLeft(DataInputStream input) {
		this.privateChannelId = new CharacterId(input);
		this.charId = new CharacterId(input);
	}
	
	public PrivateChannelCharacterLeft(long privateChannelId, long charId) {
		this.privateChannelId = new CharacterId(privateChannelId);
		this.charId = new CharacterId(charId);
	}
	
	public long getPrivateChannelId() {
		return this.privateChannelId.getData();
	}
	
	public long getCharId() {
		return this.charId.getData();
	}
	
	public int getPacketType() {
		return PrivateChannelCharacterLeft.TYPE;
	}
	
	public byte[] getBytes() throws IOException {
		return getBytes(privateChannelId, charId);
	}
	
	public String toString() {
		String output = new StringBuffer()
			.append(TYPE).append(" ").append(this.getClass().getSimpleName())
			.append("\n\tPrivateChannelId: ").append(privateChannelId)
			.append("\n\tCharId: ").append(charId)
			.toString();
	
		return output;
	}
}
