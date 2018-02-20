package com.jkbff.ao.tyrlib.packets.server;

import java.io.DataInputStream;
import java.io.IOException;

import sk.sigp.aobot.client.types.AbstractType;
import sk.sigp.aobot.client.types.CharacterId;

public class BuddyRemoved extends BaseServerPacket {

	public static final int TYPE = 41;

	protected final CharacterId charId;

	public BuddyRemoved(DataInputStream input) {
		this.charId = new CharacterId(input);
	}
	
	public BuddyRemoved(long charId) {
		this.charId = new CharacterId(charId);
	}
	
	public long getCharId() {
		return this.charId.getData();
	}
	
	public int getPacketType() {
		return BuddyRemoved.TYPE;
	}

	@Override
	public AbstractType[] getParameters() {
		return new AbstractType[]{charId};
	}
}
