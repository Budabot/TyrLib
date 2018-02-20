package com.jkbff.ao.tyrlib.packets.server;

import java.io.DataInputStream;
import java.io.IOException;

import sk.sigp.aobot.client.types.AbstractType;
import sk.sigp.aobot.client.types.Int;
import sk.sigp.aobot.client.types.Text;
import sk.sigp.aobot.client.types.CharacterId;

public class BuddyAdded extends BaseServerPacket {

	public static final int TYPE = 40;

	protected final CharacterId charId;
	protected final Int online;
	protected final Text status;
	
	public BuddyAdded(DataInputStream input) {
		this.charId = new CharacterId(input);
		this.online = new Int(input);
		this.status = new Text(input);
	}
	
	public BuddyAdded(long charId, int online, String status) {
		this.charId = new CharacterId(charId);
		this.online = new Int(online);
		this.status = new Text(status);
	}

	public long getCharId() {
		return charId.getData();
	}

	public int getOnline() {
		return online.getData();
	}

	public String getStatus() {
		return status.getData();
	}
	
	public int getPacketType() {
		return BuddyAdded.TYPE;
	}

	public AbstractType[] getParameters() {
		return new AbstractType[]{charId, online, status};
	}
}
