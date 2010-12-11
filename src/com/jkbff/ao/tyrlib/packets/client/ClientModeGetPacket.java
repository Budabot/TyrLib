package com.jkbff.ao.tyrlib.packets.client;

import java.io.DataInputStream;
import java.io.IOException;

import sk.sigp.aobot.client.types.ChatGroupId;
import sk.sigp.aobot.client.types.Int;

import com.jkbff.ao.tyrlib.packets.BaseClientPacket;

public class ClientModeGetPacket extends BaseClientPacket {

	public static final int TYPE = 70;
	public static final String NAME = "AOCP_CLIENT_MODE_GET";
	
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
}
