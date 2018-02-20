package com.jkbff.ao.tyrlib.packets.server;

import java.io.DataInputStream;
import java.io.IOException;

import sk.sigp.aobot.client.types.AbstractType;
import sk.sigp.aobot.client.types.Text;

public class Pong extends BaseServerPacket {

	public static final int TYPE = 100;

	protected final Text raw;
	
	public Pong(DataInputStream input) {
		raw = new Text(input);
	}
	
	public Pong(String raw) {
		this.raw = new Text(raw);
	}

	public String getText() {
		return raw.getData();
	}

	public int getPacketType() {
		return TYPE;
	}

	@Override
	public AbstractType[] getParameters() {
		return new AbstractType[]{raw};
	}
}
