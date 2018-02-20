package com.jkbff.ao.tyrlib.packets.server;

import java.io.DataInputStream;
import java.io.IOException;

import sk.sigp.aobot.client.types.AbstractType;
import sk.sigp.aobot.client.types.Text;

public class SimpleSystemMessage extends BaseServerPacket {

	public static final int TYPE = 36;

	protected final Text message;

	public SimpleSystemMessage(DataInputStream input) {
		this.message = new Text(input);
	}
	
	public SimpleSystemMessage(String message) {
		this.message = new Text(message);
	}
	
	public String getMessage() {
		return this.message.getData();
	}
	
	public int getPacketType() {
		return SimpleSystemMessage.TYPE;
	}

	@Override
	public AbstractType[] getParameters() {
		return new AbstractType[]{message};
	}
}
