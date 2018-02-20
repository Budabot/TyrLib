package com.jkbff.ao.tyrlib.packets.client;

import java.io.DataInputStream;
import java.io.IOException;

import sk.sigp.aobot.client.types.AbstractType;
import sk.sigp.aobot.client.types.CharacterId;

/**
 * @description Client invites a character to join its private group
 * @author Jason
 *
 */
public class PrivateChannelInvite extends BaseClientPacket {

	public static final int TYPE = 50;

	protected final CharacterId charId;
	
	public PrivateChannelInvite(DataInputStream input) {
		charId = new CharacterId(input);
	}
	
	public PrivateChannelInvite(long charId) {
		this.charId = new CharacterId(charId);
	}
	
	public long getCharId() {
		return charId.getData();
	}

	public int getPacketType() {
		return PrivateChannelInvite.TYPE;
	}

	@Override
	public AbstractType[] getParameters() {
		return new AbstractType[]{charId};
	}
}
