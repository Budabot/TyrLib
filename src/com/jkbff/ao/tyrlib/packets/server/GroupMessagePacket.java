package com.jkbff.ao.tyrlib.packets.server;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import sk.sigp.aobot.client.types.ChatGroupId;
import sk.sigp.aobot.client.types.Raw;
import sk.sigp.aobot.client.types.Text;
import sk.sigp.aobot.client.types.UserId;

import com.jkbff.ao.tyrlib.packets.BaseServerPacket;
import com.jkbff.ao.tyrlib.packets.ExtendedMessage;

public class GroupMessagePacket extends BaseServerPacket {

	public static final int TYPE = 65;
	public static final String NAME = "GROUP_MSG";
	
	private ChatGroupId chatGroupId;
	private UserId userId;
	private Text message;
	private Raw raw;
	
	private ExtendedMessage extendedMessage;

	public GroupMessagePacket(DataInputStream input) throws IOException {
		this.chatGroupId = new ChatGroupId(input);
		this.userId = new UserId(input);
		this.message = new Text(input);
		this.raw = new Raw(input);
		
		if (userId.getLongData() == 0 && message.getStringData().startsWith("~&") && message.getStringData().endsWith("~")) {
			// remove leading ~& and trailing ~
			String parseMessage = message.getStringData();
			parseMessage = parseMessage.substring(2, parseMessage.length() - 2);
			
			extendedMessage = new ExtendedMessage(new DataInputStream(new ByteArrayInputStream(parseMessage.getBytes("UTF-8"))));
		}
	}
	
	public GroupMessagePacket(int channelType, int channelId, long userId, String message, String raw) {
		this.chatGroupId = new ChatGroupId(channelId);
		this.userId = new UserId(userId);
		this.message = new Text(message);
		this.raw = new Raw(raw);
	}
	
	public long getChatGroupId() {
		return chatGroupId.getLongData();
	}

	public long getUserId() {
		return userId.getLongData();
	}

	public String getMessage() {
		return message.getStringData();
	}

	public String getRaw() {
		return raw.getStringData();
	}

	public int getPacketType() {
		return GroupMessagePacket.TYPE;
	}
	
	public ExtendedMessage getExtendedMessage() {
		return extendedMessage;
	}
	
	public byte[] getBytes() throws IOException {
		return getBytes(chatGroupId, userId, message, raw);
	}
	
	public String toString() {
		String output = new StringBuffer()
			.append(TYPE).append(" ").append(NAME).append(" (").append(this.getClass().getName()).append(")")
			.append("\n\tChatGroupId: ").append(chatGroupId)
			.append("\n\tUserId: ").append(userId)
			.append("\n\tMessage: ").append(message)
			.append("\n\tRaw: ").append(raw)
			.toString();
		
		return output;
	}
}
