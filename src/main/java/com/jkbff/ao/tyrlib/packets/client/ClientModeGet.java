package com.jkbff.ao.tyrlib.packets.client;

import java.io.DataInputStream;
import java.io.IOException;

import sk.sigp.aobot.client.types.AbstractType;
import sk.sigp.aobot.client.types.ChannelId;
import sk.sigp.aobot.client.types.Int;

public class ClientModeGet extends BaseClientPacket {

	public static final int TYPE = 70;

	protected final Int unknownInt;
	protected final ChannelId channelId;
	
	public ClientModeGet(DataInputStream input) {
		unknownInt = new Int(input);
		channelId = new ChannelId(input);
	}
	
	public ClientModeGet(int unknownInt, int channelType, int channelId) {
		this.unknownInt = new Int(unknownInt);
		this.channelId = new ChannelId(channelId);
	}
	
	public int getUnknownInt() {
		return unknownInt.getData();
	}
	
	public long getChannelId() {
		return channelId.getData();
	}

	public int getPacketType() {
		return ClientModeGet.TYPE;
	}

	@Override
	public AbstractType[] getParameters() {
		return new AbstractType[]{unknownInt, channelId};
	}
}
