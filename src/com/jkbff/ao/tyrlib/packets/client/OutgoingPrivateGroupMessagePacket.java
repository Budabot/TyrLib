package com.jkbff.ao.tyrlib.packets.client;

import java.io.DataInputStream;
import java.io.IOException;

import sk.sigp.aobot.client.types.ChatGroupId;
import sk.sigp.aobot.client.types.Raw;
import sk.sigp.aobot.client.types.Text;

import com.jkbff.ao.tyrlib.packets.BaseClientPacket;

/**
 * @description Client sends a message to a private group
 * @author Jason
 *
 */
public class OutgoingPrivateGroupMessagePacket extends BaseClientPacket {

	public static final int TYPE = 57;
	public static final String NAME = "PRIVATE_GROUP_MESSAGE";
	
	private ChatGroupId chatGroupId;
	private Text text;
	private Raw raw;
	
	public OutgoingPrivateGroupMessagePacket(DataInputStream input) throws IOException {
		chatGroupId = new ChatGroupId(input);
		text = new Text(input);
		raw = new Raw(input);
	}
	
	public OutgoingPrivateGroupMessagePacket(int chatGroupId, String text, String raw) {
		this.chatGroupId = new ChatGroupId(chatGroupId);
		this.text = new Text(text);
		this.raw = new Raw(raw);
	}
	
	public byte[] getBytes() throws IOException {
		return getBytes(chatGroupId, text, raw);
	}
	
	public int getPacketType() {
		return OutgoingPrivateGroupMessagePacket.TYPE;
	}
	
	public String toString() {
		String output = new StringBuffer()
			.append(TYPE).append(" ").append(NAME).append(" (").append(this.getClass().getName()).append(")")
			.append("\n\tChatGroupId: ").append(chatGroupId)
			.append("\n\tText: ").append(text)
			.append("\n\tRaw: ").append(raw)
			.toString();
	
		return output;
	}
}
