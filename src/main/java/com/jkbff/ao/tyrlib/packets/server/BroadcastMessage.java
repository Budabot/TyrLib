package com.jkbff.ao.tyrlib.packets.server;

import java.io.DataInputStream;
import java.io.IOException;

import sk.sigp.aobot.client.types.AbstractType;
import sk.sigp.aobot.client.types.Text;

public class BroadcastMessage extends BaseServerPacket {

	public static final int TYPE = 35;
	
	protected final Text text;
	protected final Text message;
	protected final Text raw;

	public BroadcastMessage(DataInputStream input) {
		this.text = new Text(input);
		this.message = new Text(input);
		this.raw = new Text(input);
	}
	
	public BroadcastMessage(String text, String message, String raw) {
		this.text = new Text(text);
		this.message = new Text(message);
		this.raw = new Text(raw);
	}
	
	public String getText() {
		return this.text.getData();
	}
	
	public String getMessage() {
		return this.message.getData();
	}
	
	public String getRaw() {
		return this.raw.getData();
	}
	
	public int getPacketType() {
		return BroadcastMessage.TYPE;
	}

	@Override
	public AbstractType[] getParameters() {
		return new AbstractType[]{text, message, raw};
	}
}
