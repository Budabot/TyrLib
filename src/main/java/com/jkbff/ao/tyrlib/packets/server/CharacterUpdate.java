package com.jkbff.ao.tyrlib.packets.server;

import java.io.DataInputStream;
import java.io.IOException;

import sk.sigp.aobot.client.types.AbstractType;
import sk.sigp.aobot.client.types.Text;
import sk.sigp.aobot.client.types.CharacterId;

public class CharacterUpdate extends BaseServerPacket {
	
	public static final int TYPE = 20;

	protected final CharacterId charId;
	protected final Text characterName;

	public CharacterUpdate(DataInputStream input) {
		this.charId = new CharacterId(input);
		this.characterName = new Text(input);
	}
	
	public CharacterUpdate(long charId, String characterName) {
		this.charId = new CharacterId(charId);
		this.characterName = new Text(characterName);
	}
	
	public long getCharId() {
		return this.charId.getData();
	}
	
	public String getCharacterName() {
		return this.characterName.getData();
	}
	
	public int getPacketType() {
		return CharacterUpdate.TYPE;
	}

	@Override
	public AbstractType[] getParameters() {
		return new AbstractType[]{charId, characterName};
	}
}
