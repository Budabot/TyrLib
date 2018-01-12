package com.jkbff.ao.tyrlib.packets.client;

import java.io.DataInputStream;
import java.io.IOException;

import sk.sigp.aobot.client.types.ChannelId;
import sk.sigp.aobot.client.types.Int;

public class ClientModeGetPacket extends BaseClientPacket {

	public static final int TYPE = 70;
	
	private Int unknownInt;
	private ChannelId channelId;
	
	public ClientModeGetPacket(DataInputStream input) {
		unknownInt = new Int(input);
		channelId = new ChannelId(input);
	}
	
	public ClientModeGetPacket(int unknownInt, int channelType, int channelId) {
		this.unknownInt = new Int(unknownInt);
		this.channelId = new ChannelId(channelId);
	}
	
	public int getUnknownInt() {
		return unknownInt.getData();
	}
	
	public long getChannelId() {
		return channelId.getData();
	}
	
	public byte[] getBytes() throws IOException {
		return getBytes(unknownInt, channelId);
	}
	
	public int getPacketType() {
		return ClientModeGetPacket.TYPE;
	}
	
	public String toString() {
		String output = new StringBuffer()
			.append(TYPE).append(" ").append(this.getClass().getSimpleName())
			.append("\n\tUnknownInt: ").append(unknownInt)
			.append("\n\tChannelId: ").append(channelId)
			.toString();
	
		return output;
	}
}
