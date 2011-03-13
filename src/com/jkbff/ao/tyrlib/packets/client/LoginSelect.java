package com.jkbff.ao.tyrlib.packets.client;

import java.io.DataInputStream;
import java.io.IOException;

import sk.sigp.aobot.client.types.UserId;

import com.jkbff.ao.tyrlib.packets.BaseClientPacket;

/**
 * @description Client selects character to login with.
 * @expects 
 * @author Jason
 *
 */
public class LoginSelect extends BaseClientPacket {

	public static final int TYPE = 3;
	
	private UserId userId;
	
	public LoginSelect(DataInputStream input) {
		userId = new UserId(input);
	}
	
	public LoginSelect(long userId) {
		this.userId = new UserId(userId);
	}
	
	public byte[] getBytes() throws IOException {
		return getBytes(userId);
	}
	
	public int getPacketType() {
		return LoginSelect.TYPE;
	}
	
	public String toString() {
		String output = new StringBuffer()
			.append(TYPE).append(" ").append(this.getClass().getSimpleName())
			.append("\n\tUserId: ").append(userId)
			.toString();
	
		return output;
	}
}
