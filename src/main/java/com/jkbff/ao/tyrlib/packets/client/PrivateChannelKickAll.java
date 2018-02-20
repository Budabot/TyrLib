package com.jkbff.ao.tyrlib.packets.client;

import sk.sigp.aobot.client.types.AbstractType;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * @description Client requests all users to be kicked from private group
 * @author Jason
 *
 */
public class PrivateChannelKickAll extends BaseClientPacket {

	public static final int TYPE = 54;
	
	public PrivateChannelKickAll(DataInputStream input) {
		
	}
	
	public PrivateChannelKickAll() {

	}

	public int getPacketType() {
		return PrivateChannelKickAll.TYPE;
	}

	@Override
	public AbstractType[] getParameters() {
		return new AbstractType[]{};
	}
}
