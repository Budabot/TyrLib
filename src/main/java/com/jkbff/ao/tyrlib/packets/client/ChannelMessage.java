package com.jkbff.ao.tyrlib.packets.client;

import java.io.DataInputStream;
import java.io.IOException;

import sk.sigp.aobot.client.types.ChannelId;
import sk.sigp.aobot.client.types.Text;

/**
 * @description Client sends a message to a group. 
 * @author Jason
 *
 */
public class ChannelMessage extends BaseClientPacket {

	public static final int TYPE = 65;

	protected final ChannelId channelId;
	protected final Text message;
	protected final Text raw;
	
	public ChannelMessage(DataInputStream input) {
		channelId = new ChannelId(input);
		message = new Text(input);
		raw = new Text(input);
	}
	
	public ChannelMessage(long channelId, String text, String raw) {
		this.channelId = new ChannelId(channelId);
		this.message = new Text(text);
		this.raw = new Text(raw);
	}
	
	public long getChannelId() {
		return channelId.getData();
	}

	public String getMessage() {
		return message.getData();
	}

	public String getRaw() {
		return raw.getData();
	}
	
	public byte[] getBytes() throws IOException {
		return getBytes(channelId, message, raw);
	}
	
	public int getPacketType() {
		return ChannelMessage.TYPE;
	}
	
	public String toString() {
		String output = new StringBuffer()
			.append(TYPE).append(" ").append(this.getClass().getSimpleName())
			.append("\n\tChannelId: ").append(channelId)
			.append("\n\tMessage: ").append(message)
			.append("\n\tRaw: ").append(raw)
			.toString();
	
		return output;
	}
}
