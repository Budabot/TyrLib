package com.jkbff.ao.tyrlib.packets.client;

import java.io.DataInputStream;
import java.io.IOException;

import sk.sigp.aobot.client.types.AbstractType;
import sk.sigp.aobot.client.types.CharacterId;

/**
 * @description Client requests a user to be kicked from private group
 * @author Jason
 *
 */
public class PrivateChannelKick extends BaseClientPacket {

	public static final int TYPE = 51;

	protected final CharacterId charId;
	
	public PrivateChannelKick(DataInputStream input) {
		charId = new CharacterId(input);
	}
	
	public PrivateChannelKick(long charId) {
		this.charId = new CharacterId(charId);
	}
	
	public long getCharId() {
		return charId.getData();
	}

	public int getPacketType() {
		return PrivateChannelKick.TYPE;
	}

	@Override
	public AbstractType[] getParameters() {
		return new AbstractType[]{charId};
	}
}
