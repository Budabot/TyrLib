package com.jkbff.ao.tyrlib.packets.client;

import java.io.DataInputStream;
import java.io.IOException;

import sk.sigp.aobot.client.types.CharacterId;

/**
 * @description Client selects character to login with.
 * @expects 
 * @author Jason
 *
 */
public class LoginSelect extends BaseClientPacket {

	public static final int TYPE = 3;
	
	private CharacterId charId;
	
	public LoginSelect(DataInputStream input) {
		charId = new CharacterId(input);
	}
	
	public LoginSelect(long charId) {
		this.charId = new CharacterId(charId);
	}
	
	public long getCharId() {
		return charId.getLongData();
	}
	
	public byte[] getBytes() throws IOException {
		return getBytes(charId);
	}
	
	public int getPacketType() {
		return LoginSelect.TYPE;
	}
	
	public String toString() {
		String output = new StringBuffer()
			.append(TYPE).append(" ").append(this.getClass().getSimpleName())
			.append("\n\tCharId: ").append(charId)
			.toString();
	
		return output;
	}
}
