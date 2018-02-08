package com.jkbff.ao.tyrlib.packets.client;

import java.io.DataInputStream;
import java.io.IOException;

import sk.sigp.aobot.client.types.ChannelId;
import sk.sigp.aobot.client.types.Int;

public class PublicChannelClientModeSet extends BaseClientPacket {

	public static final int TYPE = 66;

	protected final ChannelId channelId;
	protected final Int unknownInt1;
	protected final Int unknownInt2;
	protected final Int unknownInt3;
	protected final Int unknownInt4;
	
	public PublicChannelClientModeSet(DataInputStream input) {
		channelId = new ChannelId(input);
		unknownInt1 = new Int(input);
		unknownInt2 = new Int(input);
		unknownInt3 = new Int(input);
		unknownInt4 = new Int(input);
	}
	
	public PublicChannelClientModeSet(int channelId, int unknownInt1, int unknownInt2, int unknownInt3, int unknownInt4) {
		this.channelId = new ChannelId(channelId);
		this.unknownInt1 = new Int(unknownInt1);
		this.unknownInt2 = new Int(unknownInt2);
		this.unknownInt3 = new Int(unknownInt3);
		this.unknownInt4 = new Int(unknownInt4);
	}
	
	public long getChannelId() {
		return channelId.getData();
	}
	
	public int getUnknownInt1() {
		return unknownInt1.getData();
	}
	
	public int getUnknownInt2() {
		return unknownInt2.getData();
	}
	
	public int getUnknownInt3() {
		return unknownInt3.getData();
	}
	
	public int getUnknownInt4() {
		return unknownInt4.getData();
	}
	
	public byte[] getBytes() throws IOException {
		return getBytes(channelId, unknownInt1, unknownInt2, unknownInt3, unknownInt4);
	}
	
	public int getPacketType() {
		return PublicChannelClientModeSet.TYPE;
	}
	
	public String toString() {
		String output = new StringBuffer()
			.append(TYPE).append(" ").append(this.getClass().getSimpleName())
			.append("\n\tChannelId: ").append(channelId)
			.append("\n\tUnknownInt1: ").append(unknownInt1)
			.append("\n\tUnknownInt2: ").append(unknownInt2)
			.append("\n\tUnknownInt3: ").append(unknownInt3)
			.append("\n\tUnknownInt4: ").append(unknownInt4)
			.toString();
	
		return output;
	}
}
