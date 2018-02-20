package com.jkbff.ao.tyrlib.packets.client;

import java.io.DataInputStream;
import java.io.IOException;

import sk.sigp.aobot.client.types.AbstractType;
import sk.sigp.aobot.client.types.CharacterId;

/**
 * @description Client accepts an invitation to join a private group
 * @responseTo com.jkbff.ao.tyrlib.packets.server.PrivateGroupJoinRequestPacket
 * @author Jason
 *
 */
public class PrivateChannelAccept extends BaseClientPacket {

	public static final int TYPE = 52;

	protected final CharacterId charId;
	
	public PrivateChannelAccept(DataInputStream input) {
		charId = new CharacterId(input);
	}
	
	public PrivateChannelAccept(long charId) {
		this.charId = new CharacterId(charId);
	}
	
	public long getCharId() {
		return charId.getData();
	}

	public int getPacketType() {
		return PrivateChannelAccept.TYPE;
	}

	@Override
	public AbstractType[] getParameters() {
		return new AbstractType[]{charId};
	}
}
