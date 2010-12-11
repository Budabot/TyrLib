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
public class LoginSelectCharacterPacket extends BaseClientPacket {

	public static final int TYPE = 3;
	public static final String NAME = "AOCP_LOGIN_SELCHAR";
	
	private UserId userId;
	
	public LoginSelectCharacterPacket(DataInputStream input) throws IOException {
		userId = new UserId(input);
	}
	
	public LoginSelectCharacterPacket(long userId) {
		this.userId = new UserId(userId);
	}
	
	public byte[] getBytes() throws IOException {
		
		return getBytes(userId);
	}
	
	public int getPacketType() {
		return LoginSelectCharacterPacket.TYPE;
	}
}
