package com.jkbff.ao.tyrlib.packets.server;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.jkbff.ao.tyrlib.chat.MMDBParser;
import com.jkbff.ao.tyrlib.packets.ExtendedMessageParser;
import sk.sigp.aobot.client.types.ChatGroupId;
import sk.sigp.aobot.client.types.Text;
import sk.sigp.aobot.client.types.CharacterId;

import com.jkbff.ao.tyrlib.packets.ExtendedMessage;

public class ChannelMessage extends BaseServerPacket {

	public static final int TYPE = 65;
	
	private ChatGroupId chatGroupId;
	private CharacterId charId;
	private Text message;
	private Text raw;

	public ChannelMessage(DataInputStream input) {
		this.chatGroupId = new ChatGroupId(input);
		this.charId = new CharacterId(input);
		this.message = new Text(input);
		this.raw = new Text(input);
	}
	
	public ChannelMessage(long channelId, long charId, String message, String raw) {
		this.chatGroupId = new ChatGroupId(channelId);
		this.charId = new CharacterId(charId);
		this.message = new Text(message);
		this.raw = new Text(raw);
	}
	
	public long getChatGroupId() {
		return chatGroupId.getLongData();
	}

	public long getCharId() {
		return charId.getLongData();
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
	
	public ExtendedMessage getExtendedMessage(MMDBParser mmdbParser) throws IOException {
		String parseMessage = message.getStringData();
		if (charId.getLongData() == 0 && parseMessage.startsWith("~&") && parseMessage.endsWith("~")) {
			// remove leading ~& and trailing ~
			parseMessage = parseMessage.substring(2, parseMessage.length() - 1);

			ExtendedMessageParser extendedMessageParser = new ExtendedMessageParser(mmdbParser);

			return extendedMessageParser.parse(new DataInputStream(new ByteArrayInputStream(parseMessage.getBytes("UTF-8"))));
		} else {
			return null;
		}
	}
	
	public byte[] getBytes() throws IOException {
		return getBytes(chatGroupId, charId, message, raw);
	}
	
	public String toString() {
		String output = new StringBuffer()
			.append(TYPE).append(" ").append(this.getClass().getSimpleName())
			.append("\n\tChatGroupId: ").append(chatGroupId)
			.append("\n\tCharId: ").append(charId)
			.append("\n\tMessage: ").append(message)
			.append("\n\tRaw: ").append(raw)
			.toString();
		
		return output;
	}
}
