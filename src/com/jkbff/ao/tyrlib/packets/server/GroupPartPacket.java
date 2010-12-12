package com.jkbff.ao.tyrlib.packets.server;

import java.io.DataInputStream;
import java.io.IOException;

import sk.sigp.aobot.client.types.ChatGroupId;

import com.jkbff.ao.tyrlib.packets.BaseServerPacket;

public class GroupPartPacket extends BaseServerPacket {

	public static final int TYPE = 61;
	public static final String NAME = "GROUP_PART";
	
	private ChatGroupId chatGroupId;

	public GroupPartPacket(DataInputStream input) throws IOException {
		this.chatGroupId = new ChatGroupId(input);
	}
	
	public GroupPartPacket(int channelType, int channelId) {
		this.chatGroupId = new ChatGroupId(channelId);
	}

	public long getChatGroupId() {
		return chatGroupId.getLongData();
	}

	public int getPacketType() {
		return GroupPartPacket.TYPE;
	}

	public byte[] getBytes() throws IOException {
		return getBytes(chatGroupId);
	}
	
	public String toString() {
		String output = new StringBuffer()
			.append(TYPE).append(" ").append(NAME).append(" (").append(this.getClass().getName()).append(")")
			.append("\n\tChatGroupId: ").append(chatGroupId)
			.toString();
	
		return output;
	}
}
