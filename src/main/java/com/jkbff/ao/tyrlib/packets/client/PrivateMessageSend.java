package com.jkbff.ao.tyrlib.packets.client;

import java.io.DataInputStream;
import java.io.IOException;

import sk.sigp.aobot.client.types.AbstractType;
import sk.sigp.aobot.client.types.CharacterId;
import sk.sigp.aobot.client.types.Text;

/**
 * @description Client sends private message. 
 * @author Jason
 *
 */
public class PrivateMessageSend extends BaseClientPacket {

	public static final int TYPE = 30;

	protected final CharacterId charId;
	protected final Text message;
	protected final Text raw;
	
	public PrivateMessageSend(DataInputStream input) {
		charId = new CharacterId(input);
		message = new Text(input);
		raw = new Text(input);
	}
	
	public PrivateMessageSend(long charId, String text, String raw) {
		this.charId = new CharacterId(charId);
		this.message = new Text(text);
		this.raw = new Text(raw);
	}
	
	public long getCharId() {
		return charId.getData();
	}
	
	public String getMessage() {
		return message.getData();
	}
	
	public String getRaw() {
		return raw.getData();
	}

	public int getPacketType() {
		return PrivateMessageSend.TYPE;
	}

	@Override
	public AbstractType[] getParameters() {
		return new AbstractType[]{charId, message, raw};
	}
}
