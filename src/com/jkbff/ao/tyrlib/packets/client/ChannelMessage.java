package com.jkbff.ao.tyrlib.packets.client;

import java.io.DataInputStream;
import java.io.IOException;

import sk.sigp.aobot.client.types.ChatGroupId;
import sk.sigp.aobot.client.types.Text;

import com.jkbff.ao.tyrlib.packets.BaseClientPacket;

/**
 * @description Client sends a message to a group. 
 * @author Jason
 *
 */
public class ChannelMessage extends BaseClientPacket {

	public static final int TYPE = 65;
	
	private ChatGroupId chatGroupId;
	private Text text;
	private Text raw;
	
	public ChannelMessage(DataInputStream input) {
		chatGroupId = new ChatGroupId(input);
		text = new Text(input);
		raw = new Text(input);
	}
	
	public ChannelMessage(long chatGroupId, String text, String raw) {
		this.chatGroupId = new ChatGroupId(chatGroupId);
		this.text = new Text(text);
		this.raw = new Text(raw);
	}
	
	public byte[] getBytes() throws IOException {
		return getBytes(chatGroupId, text, raw);
	}
	
	public int getPacketType() {
		return ChannelMessage.TYPE;
	}
	
	public String toString() {
		String output = new StringBuffer()
			.append(TYPE).append(" ").append(this.getClass().getSimpleName())
			.append("\n\tChatGroupId: ").append(chatGroupId)
			.append("\n\tText: ").append(text)
			.append("\n\tRaw: ").append(raw)
			.toString();
	
		return output;
	}
}
