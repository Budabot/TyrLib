package com.jkbff.ao.tyrlib.packets.client;

import java.io.DataInputStream;
import java.io.IOException;

import sk.sigp.aobot.client.types.AbstractType;
import sk.sigp.aobot.client.types.CharacterId;

public class BuddyRemove extends BaseClientPacket {

	public static final int TYPE = 41;

	protected final CharacterId charId;
	
	public BuddyRemove(DataInputStream input) {
		this.charId = new CharacterId(input);
	}
	
	public BuddyRemove(long charId) {
		this.charId = new CharacterId(charId);
	}
	
	public long getCharId() {
		return charId.getData();
	}

	public int getPacketType() {
		return BuddyRemove.TYPE;
	}

	@Override
	public AbstractType[] getParameters() {
		return new AbstractType[]{charId};
	}
}
