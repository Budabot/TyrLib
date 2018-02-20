package com.jkbff.ao.tyrlib.packets.server;

import java.io.DataInputStream;
import java.io.IOException;

import sk.sigp.aobot.client.types.AbstractType;
import sk.sigp.aobot.client.types.CharacterId;

public class PrivateChannelInviteRefused extends BaseServerPacket {

	public static final int TYPE = 58;

	protected final CharacterId privateChannelId;
	protected final CharacterId charId;

	public PrivateChannelInviteRefused(DataInputStream input) {
		this.privateChannelId = new CharacterId(input);
		this.charId = new CharacterId(input);
	}
	
	public PrivateChannelInviteRefused(long privateChannelId, long charId) {
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
		return PrivateChannelInviteRefused.TYPE;
	}

	@Override
	public AbstractType[] getParameters() {
		return new AbstractType[]{privateChannelId, charId};
	}
}
