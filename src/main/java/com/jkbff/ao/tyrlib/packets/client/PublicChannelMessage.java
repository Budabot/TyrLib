package com.jkbff.ao.tyrlib.packets.client;

import java.io.DataInputStream;
import java.io.IOException;

import sk.sigp.aobot.client.types.AbstractType;
import sk.sigp.aobot.client.types.ChannelId;
import sk.sigp.aobot.client.types.Text;

/**
 * @description Client sends a message to a group. 
 * @author Jason
 *
 */
public class PublicChannelMessage extends BaseClientPacket {

	public static final int TYPE = 65;

	protected final ChannelId channelId;
	protected final Text message;
	protected final Text raw;
	
	public PublicChannelMessage(DataInputStream input) {
		channelId = new ChannelId(input);
		message = new Text(input);
		raw = new Text(input);
	}
	
	public PublicChannelMessage(long channelId, String text, String raw) {
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

	public int getPacketType() {
		return PublicChannelMessage.TYPE;
	}

	@Override
	public AbstractType[] getParameters() {
		return new AbstractType[]{channelId, message, raw};
	}
}
