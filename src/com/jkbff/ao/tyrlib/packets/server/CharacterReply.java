package com.jkbff.ao.tyrlib.packets.server;

import java.io.DataInputStream;
import java.io.IOException;

import sk.sigp.aobot.client.types.Text;
import sk.sigp.aobot.client.types.UserId;

import com.jkbff.ao.tyrlib.packets.BaseServerPacket;

public class CharacterReply extends BaseServerPacket {

	public static final int TYPE = 21;
	
	private UserId userId;
	private Text characterName;

	public CharacterReply(DataInputStream input) {
		this.userId = new UserId(input);
		this.characterName = new Text(input);
	}
	
	public CharacterReply(long userId, String characterName) {
		this.userId = new UserId(userId);
		this.characterName = new Text(characterName);
	}
	
	public long getUserId() {
		return this.userId.getLongData();
	}
	
	public String getCharacterName() {
		return this.characterName.getStringData();
	}

	public int getPacketType() {
		return CharacterReply.TYPE;
	}
	
	public byte[] getBytes() throws IOException {
		return getBytes(userId, characterName);
	}
	
	public String toString() {
		String output = new StringBuffer()
			.append(TYPE).append(" ").append(this.getClass().getSimpleName())
			.append("\n\tUserId: ").append(userId)
			.append("\n\tCharacterName: ").append(characterName)
			.toString();
	
		return output;
	}
}