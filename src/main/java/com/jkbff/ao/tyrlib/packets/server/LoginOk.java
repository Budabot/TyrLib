package com.jkbff.ao.tyrlib.packets.server;

import java.io.DataInputStream;
import java.io.IOException;

import sk.sigp.aobot.client.types.AbstractType;

public class LoginOk extends BaseServerPacket {

	public static final int TYPE = 5;
	
	public LoginOk(DataInputStream input) {
		
	}
	
	public LoginOk() {
		
	}

	public int getPacketType() {
		return LoginOk.TYPE;
	}

	@Override
	public AbstractType[] getParameters() {
		return new AbstractType[]{};
	}
}
