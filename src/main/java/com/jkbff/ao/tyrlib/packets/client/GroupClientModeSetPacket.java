package com.jkbff.ao.tyrlib.packets.client;

import java.io.DataInputStream;
import java.io.IOException;

import sk.sigp.aobot.client.types.ChatGroupId;
import sk.sigp.aobot.client.types.Int;

public class GroupClientModeSetPacket extends BaseClientPacket {

	public static final int TYPE = 66;
	
	private ChatGroupId chatGroupId;
	private Int unknownInt1;
	private Int unknownInt2;
	private Int unknownInt3;
	private Int unknownInt4;
	
	public GroupClientModeSetPacket(DataInputStream input) {
		chatGroupId = new ChatGroupId(input);
		unknownInt1 = new Int(input);
		unknownInt2 = new Int(input);
		unknownInt3 = new Int(input);
		unknownInt4 = new Int(input);
	}
	
	public GroupClientModeSetPacket(int chatGroupId, int unknownInt1, int unknownInt2, int unknownInt3, int unknownInt4) {
		this.chatGroupId = new ChatGroupId(chatGroupId);
		this.unknownInt1 = new Int(unknownInt1);
		this.unknownInt2 = new Int(unknownInt2);
		this.unknownInt3 = new Int(unknownInt3);
		this.unknownInt4 = new Int(unknownInt4);
	}
	
	public long getChatGroupId() {
		return chatGroupId.getLongData();
	}
	
	public int getUnknownInt1() {
		return unknownInt1.getIntData();
	}
	
	public int getUnknownInt2() {
		return unknownInt2.getIntData();
	}
	
	public int getUnknownInt3() {
		return unknownInt3.getIntData();
	}
	
	public int getUnknownInt4() {
		return unknownInt4.getIntData();
	}
	
	public byte[] getBytes() throws IOException {
		return getBytes(chatGroupId, unknownInt1, unknownInt2, unknownInt3, unknownInt4);
	}
	
	public int getPacketType() {
		return GroupClientModeSetPacket.TYPE;
	}
	
	public String toString() {
		String output = new StringBuffer()
			.append(TYPE).append(" ").append(this.getClass().getSimpleName())
			.append("\n\tChatGroupId: ").append(chatGroupId)
			.append("\n\tUnknownInt1: ").append(unknownInt1)
			.append("\n\tUnknownInt2: ").append(unknownInt2)
			.append("\n\tUnknownInt3: ").append(unknownInt3)
			.append("\n\tUnknownInt4: ").append(unknownInt4)
			.toString();
	
		return output;
	}
}
