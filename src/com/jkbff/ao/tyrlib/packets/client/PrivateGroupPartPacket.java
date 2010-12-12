package com.jkbff.ao.tyrlib.packets.client;

import java.io.DataInputStream;
import java.io.IOException;

import sk.sigp.aobot.client.types.UserId;

import com.jkbff.ao.tyrlib.packets.BaseClientPacket;

/**
 * @description Purpose unknown
 * @author Jason
 *
 */
public class PrivateGroupPartPacket extends BaseClientPacket {

	public static final int TYPE = 53;
	public static final String NAME = "PRIVATE_GROUP_PART";
	
	private UserId userId;
	
	public PrivateGroupPartPacket(DataInputStream input) throws IOException {
		userId = new UserId(input);
	}
	
	public PrivateGroupPartPacket(long userId) {
		this.userId = new UserId(userId);
	}
	
	public byte[] getBytes() throws IOException {
		return getBytes(userId);
	}
	
	public int getPacketType() {
		return PrivateGroupPartPacket.TYPE;
	}
	
	public String toString() {
		String output = new StringBuffer()
			.append(TYPE).append(" ").append(NAME).append(" (").append(this.getClass().getName()).append(")")
			.append("\n\tUserId: ").append(userId)
			.toString();
	
		return output;
	}
}
