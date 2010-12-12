package com.jkbff.ao.tyrlib.packets.client;

import java.io.DataInputStream;
import java.io.IOException;

import sk.sigp.aobot.client.types.UserId;

import com.jkbff.ao.tyrlib.packets.BaseClientPacket;

/**
 * @description Client requests a user to be kicked from private group
 * @author Jason
 *
 */
public class PrivateGroupKickRequestPacket extends BaseClientPacket {

	public static final int TYPE = 51;
	public static final String NAME = "PRIVATE_GROUP_KICK";
	
	private UserId userId;
	
	public PrivateGroupKickRequestPacket(DataInputStream input) throws IOException {
		userId = new UserId(input);
	}
	
	public PrivateGroupKickRequestPacket(long userId) {
		this.userId = new UserId(userId);
	}
	
	public byte[] getBytes() throws IOException {
		return getBytes(userId);
	}
	
	public int getPacketType() {
		return PrivateGroupKickRequestPacket.TYPE;
	}
	
	public String toString() {
		String output = new StringBuffer()
			.append(TYPE).append(" ").append(NAME).append(" (").append(this.getClass().getName()).append(")")
			.append("\n\tUserId: ").append(userId)
			.toString();
	
		return output;
	}
}
