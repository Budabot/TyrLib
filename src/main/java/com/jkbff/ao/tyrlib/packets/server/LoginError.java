package com.jkbff.ao.tyrlib.packets.server;

import java.io.DataInputStream;
import java.io.IOException;

import sk.sigp.aobot.client.types.AbstractType;
import sk.sigp.aobot.client.types.Text;

public class LoginError extends BaseServerPacket {
	
	public static final int TYPE = 6;

	protected final Text message;

	public LoginError(DataInputStream input) {
		this.message = new Text(input);
	}
	
	public LoginError(String message) {
		this.message = new Text(message);
	}
	
	public String getMessage() {
		return this.message.getData();
	}

	public int getPacketType() {
		return LoginError.TYPE;
	}

	@Override
	public AbstractType[] getParameters() {
		return new AbstractType[]{message};
	}
}
