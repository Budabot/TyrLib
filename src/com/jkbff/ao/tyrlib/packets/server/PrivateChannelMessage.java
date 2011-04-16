package com.jkbff.ao.tyrlib.packets.server;

import java.io.DataInputStream;
import java.io.IOException;

import sk.sigp.aobot.client.types.Text;
import sk.sigp.aobot.client.types.CharacterId;

import com.jkbff.ao.tyrlib.packets.BaseServerPacket;

public class PrivateChannelMessage extends BaseServerPacket {

	public static final int TYPE = 57;
	
	private CharacterId charId1;
	private CharacterId charId2;
	private Text message;
	private Text raw;

	public PrivateChannelMessage(DataInputStream input) {
		this.charId1 = new CharacterId(input);
		this.charId2 = new CharacterId(input);
		this.message = new Text(input);
		this.raw = new Text(input);
	}
	
	public PrivateChannelMessage(long charId1, long charId2, String message, String raw) {
		this.charId1 = new CharacterId(charId1);
		this.charId2 = new CharacterId(charId2);
		this.message = new Text(message);
		this.raw = new Text(raw);
	}
	
	public long getCharId1() {
		return this.charId1.getLongData();
	}
	
	public long getCharId2() {
		return this.charId2.getLongData();
	}
	
	public String getMessage() {
		return this.message.getStringData();
	}
	
	public String getRaw() {
		return this.raw.getStringData();
	}
	
	public int getPacketType() {
		return PrivateChannelMessage.TYPE;
	}
	
	public byte[] getBytes() throws IOException {
		return getBytes(charId1, charId2, message, raw);
	}
	
	public String toString() {
		String output = new StringBuffer()
			.append(TYPE).append(" ").append(this.getClass().getSimpleName())
			.append("\n\tCharId1: ").append(charId1)
			.append("\n\tCharId2: ").append(charId2)
			.append("\n\tMessage: ").append(message)
			.append("\n\tRaw: ").append(raw)
			.toString();
	
		return output;
	}
}
