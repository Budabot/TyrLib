package com.jkbff.ao.tyrlib.packets.client;

import java.io.DataInputStream;
import java.io.IOException;

import sk.sigp.aobot.client.types.AbstractType;
import sk.sigp.aobot.client.types.Text;

public class CharacterLookup extends BaseClientPacket {

	public static final int TYPE = 21;

	protected final Text name;
	
	public CharacterLookup(DataInputStream input) {
		name = new Text(input);
	}
	
	public CharacterLookup(String name) {
		this.name = new Text(name);
	}
	
	public String getName() {
		return name.getData();
	}

	public int getPacketType() {
		return CharacterLookup.TYPE;
	}

	@Override
	public AbstractType[] getParameters() {
		return new AbstractType[]{name};
	}
}
