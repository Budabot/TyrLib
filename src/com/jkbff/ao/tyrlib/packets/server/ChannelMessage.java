package com.jkbff.ao.tyrlib.packets.server;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import sk.sigp.aobot.client.types.ChatGroupId;
import sk.sigp.aobot.client.types.Text;
import sk.sigp.aobot.client.types.UserId;

import com.jkbff.ao.tyrlib.packets.BaseServerPacket;
import com.jkbff.ao.tyrlib.packets.ExtendedMessage;

public class ChannelMessage extends BaseServerPacket {

	public static final int TYPE = 65;
	
	private ChatGroupId chatGroupId;
	private UserId userId;
	private Text message;
	private Text raw;
	
	private ExtendedMessage extendedMessage;

	public ChannelMessage(DataInputStream input) {
		this.chatGroupId = new ChatGroupId(input);
		this.userId = new UserId(input);
		this.message = new Text(input);
		this.raw = new Text(input);
	}
	
	public ChannelMessage(int channelType, int channelId, long userId, String message, String raw) {
		this.chatGroupId = new ChatGroupId(channelId);
		this.userId = new UserId(userId);
		this.message = new Text(message);
		this.raw = new Text(raw);
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
		return ChannelMessage.TYPE;
	}
	
	public ExtendedMessage getExtendedMessage() throws IOException {
		if (extendedMessage == null && userId.getLongData() == 0 && message.getStringData().startsWith("~&") && message.getStringData().endsWith("~")) {
			// remove leading ~& and trailing ~
			String parseMessage = message.getStringData();
			parseMessage = parseMessage.substring(2, parseMessage.length() - 2);

			extendedMessage = new ExtendedMessage(new DataInputStream(new ByteArrayInputStream(parseMessage.getBytes("UTF-8"))));
		}
		return extendedMessage;
	}
	
	public byte[] getBytes() throws IOException {
		return getBytes(chatGroupId, userId, message, raw);
	}
	
	public String toString() {
		String output = new StringBuffer()
			.append(TYPE).append(" ").append(this.getClass().getSimpleName())
			.append("\n\tChatGroupId: ").append(chatGroupId)
			.append("\n\tUserId: ").append(userId)
			.append("\n\tMessage: ").append(message)
			.append("\n\tRaw: ").append(raw)
			.toString();
		
		return output;
	}
}