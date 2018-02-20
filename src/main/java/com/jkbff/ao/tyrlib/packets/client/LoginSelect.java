package com.jkbff.ao.tyrlib.packets.client;

import java.io.DataInputStream;
import java.io.IOException;

import sk.sigp.aobot.client.types.AbstractType;
import sk.sigp.aobot.client.types.CharacterId;

/**
 * @description Client selects character to login with.
 * @expects 
 * @author Jason
 *
 */
public class LoginSelect extends BaseClientPacket {

	public static final int TYPE = 3;

	protected final CharacterId charId;
	
	public LoginSelect(DataInputStream input) {
		charId = new CharacterId(input);
	}
	
	public LoginSelect(long charId) {
		this.charId = new CharacterId(charId);
	}
	
	public long getCharId() {
		return charId.getData();
	}

	public int getPacketType() {
		return LoginSelect.TYPE;
	}

	@Override
	public AbstractType[] getParameters() {
		return new AbstractType[]{charId};
	}
}
