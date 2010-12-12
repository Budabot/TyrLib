package com.jkbff.ao.tyrlib.packets.client;

import java.io.DataInputStream;
import java.io.IOException;

import sk.sigp.aobot.client.types.Int;
import sk.sigp.aobot.client.types.Text;

import com.jkbff.ao.tyrlib.packets.BaseClientPacket;

/**
 * @description Client sends a request to login. 
 * @expects 
 * @author Jason
 *
 */
public class LoginRequestPacket extends BaseClientPacket {
	
	public static final int TYPE = 2;
	public static final String NAME = "AOCP_LOGIN_REQUEST";
	
	private Int unknownInt;
	private Text username;
	private Text key;
	
	public LoginRequestPacket(DataInputStream input) throws IOException {
		unknownInt = new Int(input);
		username = new Text(input);
		key = new Text(input);
	}
	
	public LoginRequestPacket(int unknownInt, String username, String key) {
		this.unknownInt = new Int(unknownInt);
		this.username = new Text(username);
		this.key = new Text(key);
	}
	
	public byte[] getBytes() throws IOException {
		return getBytes(unknownInt, username, key);
	}

	public int getPacketType() {
		return LoginRequestPacket.TYPE;
	}
	
	public String toString() {
		String output = new StringBuffer()
			.append(TYPE).append(" ").append(NAME).append(" (").append(this.getClass().getName()).append(")")
			.append("\n\tUnknownInt: ").append(unknownInt)
			.append("\n\tUsername: ").append(username)
			.append("\n\tKey: ").append(key)
			.toString();
	
		return output;
	}
}
