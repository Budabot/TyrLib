package com.jkbff.ao.tyrlib.packets.server;

import java.io.DataInputStream;
import java.io.IOException;

import sk.sigp.aobot.client.types.ChatGroupId;

import com.jkbff.ao.tyrlib.packets.BaseServerPacket;

public class ChannelLeave extends BaseServerPacket {

	public static final int TYPE = 61;
	
	private ChatGroupId chatGroupId;

	public ChannelLeave(DataInputStream input) {
		this.chatGroupId = new ChatGroupId(input);
	}
	
	public ChannelLeave(int channelType, int channelId) {
		this.chatGroupId = new ChatGroupId(channelId);
	}

	public long getChatGroupId() {
		return chatGroupId.getLongData();
	}

	public int getPacketType() {
		return ChannelLeave.TYPE;
	}

	public byte[] getBytes() throws IOException {
		return getBytes(chatGroupId);
	}
	
	public String toString() {
		String output = new StringBuffer()
			.append(TYPE).append(" ").append(this.getClass().getSimpleName())
			.append("\n\tChatGroupId: ").append(chatGroupId)
			.toString();
	
		return output;
	}
}
