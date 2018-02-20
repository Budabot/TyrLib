package com.jkbff.ao.tyrlib.packets.server;

import java.io.DataInputStream;
import java.io.IOException;

import sk.sigp.aobot.client.types.AbstractType;
import sk.sigp.aobot.client.types.Text;
import sk.sigp.aobot.client.types.CharacterId;

public class PrivateMessageReceive extends BaseServerPacket {

	public static final int TYPE = 30;

	protected final CharacterId charId;
	protected final Text message;
	protected final Text raw;

	public PrivateMessageReceive(DataInputStream input) {
		this.charId = new CharacterId(input);
		this.message = new Text(input);
		this.raw = new Text(input);
	}
	
	public PrivateMessageReceive(long charId, String message, String raw) {
		this.charId = new CharacterId(charId);
		this.message = new Text(message);
		this.raw = new Text(raw);
	}
	
	public long getCharId() {
		return this.charId.getData();
	}
	
	public String getMessage() {
		return this.message.getData();
	}
	
	public String getRaw() {
		return this.raw.getData();
	}

	public int getPacketType() {
		return PrivateMessageReceive.TYPE;
	}

	@Override
	public AbstractType[] getParameters() {
		return new AbstractType[]{charId, message, raw};
	}
}
