package com.jkbff.ao.tyrlib.packets.client;

import java.io.DataInputStream;
import java.io.IOException;

import sk.sigp.aobot.client.types.AbstractType;
import sk.sigp.aobot.client.types.Text;

/**
 * @description Client sends a ping message
 * @expects com.jkbff.ao.tyrlib.packets.server.PongPacket
 * @author Jason
 *
 */
public class Ping extends BaseClientPacket {

	public static final int TYPE = 100;

	protected final Text raw;
	
	public Ping(DataInputStream input) {
		raw = new Text(input);
	}
	
	public Ping(String raw) {
		this.raw = new Text(raw);
	}
	
	public String getText() {
		return raw.getData();
	}

	public int getPacketType() {
		return Ping.TYPE;
	}

	@Override
	public AbstractType[] getParameters() {
		return new AbstractType[]{raw};
	}
}
