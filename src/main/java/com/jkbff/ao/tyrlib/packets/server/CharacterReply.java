package com.jkbff.ao.tyrlib.packets.server;

import java.io.DataInputStream;
import java.io.IOException;

import sk.sigp.aobot.client.types.Text;
import sk.sigp.aobot.client.types.CharacterId;

public class CharacterReply extends BaseServerPacket {

	public static final int TYPE = 21;
	
	private CharacterId charId;
	private Text characterName;

	public CharacterReply(DataInputStream input) {
		this.charId = new CharacterId(input);
		this.characterName = new Text(input);
	}
	
	public CharacterReply(long charId, String characterName) {
		this.charId = new CharacterId(charId);
		this.characterName = new Text(characterName);
	}
	
	public long getCharId() {
		return this.charId.getLongData();
	}
	
	public String getCharacterName() {
		return this.characterName.getStringData();
	}

	public int getPacketType() {
		return CharacterReply.TYPE;
	}
	
	public byte[] getBytes() throws IOException {
		return getBytes(charId, characterName);
	}
	
	public String toString() {
		String output = new StringBuffer()
			.append(TYPE).append(" ").append(this.getClass().getSimpleName())
			.append("\n\tCharId: ").append(charId)
			.append("\n\tCharacterName: ").append(characterName)
			.toString();
	
		return output;
	}
}
