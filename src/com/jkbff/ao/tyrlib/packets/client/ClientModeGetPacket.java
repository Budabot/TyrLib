package com.jkbff.ao.tyrlib.packets.client;

import java.io.DataInputStream;
import java.io.IOException;

import sk.sigp.aobot.client.types.ChatGroupId;
import sk.sigp.aobot.client.types.Int;

import com.jkbff.ao.tyrlib.packets.BaseClientPacket;

public class ClientModeGetPacket extends BaseClientPacket {

	public static final int TYPE = 70;
	
	private Int unknownInt;
	private ChatGroupId chatGroupId;
	
	public ClientModeGetPacket(DataInputStream input) throws IOException {
		unknownInt = new Int(input);
		chatGroupId = new ChatGroupId(input);
	}
	
	public ClientModeGetPacket(int unknownInt, int channelType, int channelId) {
		this.unknownInt = new Int(unknownInt);
		this.chatGroupId = new ChatGroupId(channelId);
	}
	
	public byte[] getBytes() throws IOException {
		return getBytes(unknownInt, chatGroupId);
	}
	
	public int getPacketType() {
		return ClientModeGetPacket.TYPE;
	}
	
	public String toString() {
		String output = new StringBuffer()
			.append(TYPE).append(" ").append(this.getClass().getSimpleName())
			.append("\n\tUnknownInt: ").append(unknownInt)
			.append("\n\tChatGroupId: ").append(chatGroupId)
			.toString();
	
		return output;
	}
}
