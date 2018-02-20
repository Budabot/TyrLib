package com.jkbff.ao.tyrlib.packets.server;

import java.io.DataInputStream;
import java.io.IOException;

import sk.sigp.aobot.client.types.AbstractType;
import sk.sigp.aobot.client.types.Text;
import sk.sigp.aobot.client.types.CharacterId;

public class PrivateChannelMessage extends BaseServerPacket {

	public static final int TYPE = 57;

	protected final CharacterId privateChannelId;
	protected final CharacterId charId;
	protected final Text message;
	protected final Text raw;

	public PrivateChannelMessage(DataInputStream input) {
		this.privateChannelId = new CharacterId(input);
		this.charId = new CharacterId(input);
		this.message = new Text(input);
		this.raw = new Text(input);
	}
	
	public PrivateChannelMessage(long privateChannelId, long charId, String message, String raw) {
		this.privateChannelId = new CharacterId(privateChannelId);
		this.charId = new CharacterId(charId);
		this.message = new Text(message);
		this.raw = new Text(raw);
	}
	
	public long getPrivateChannelId() {
		return this.privateChannelId.getData();
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
		return PrivateChannelMessage.TYPE;
	}
	
	@Override
	public AbstractType[] getParameters() {
		return new AbstractType[]{privateChannelId, charId, message, raw};
	}
}
