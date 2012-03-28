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
public class LoginRequest extends BaseClientPacket {
	
	public static final int TYPE = 2;
	
	private Int unknownInt;
	private Text username;
	private Text key;
	
	public LoginRequest(DataInputStream input) {
		unknownInt = new Int(input);
		username = new Text(input);
		key = new Text(input);
	}
	
	public LoginRequest(int unknownInt, String username, String key) {
		this.unknownInt = new Int(unknownInt);
		this.username = new Text(username);
		this.key = new Text(key);
	}
	
	public int getUnknownInt() {
		return unknownInt.getIntData();
	}
	
	public String getUsername() {
		return username.getStringData();
	}
	
	public String getKey() {
		return key.getStringData();
	}
	
	public byte[] getBytes() throws IOException {
		return getBytes(unknownInt, username, key);
	}

	public int getPacketType() {
		return LoginRequest.TYPE;
	}
	
	public String toString() {
		String output = new StringBuffer()
			.append(TYPE).append(" ").append(this.getClass().getSimpleName())
			.append("\n\tUnknownInt: ").append(unknownInt)
			.append("\n\tUsername: ").append(username)
			.append("\n\tKey: ").append(key)
			.toString();
	
		return output;
	}
}
