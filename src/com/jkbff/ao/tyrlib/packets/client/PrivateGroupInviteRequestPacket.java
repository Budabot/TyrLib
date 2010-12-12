package com.jkbff.ao.tyrlib.packets.client;

import java.io.DataInputStream;
import java.io.IOException;

import sk.sigp.aobot.client.types.UserId;

import com.jkbff.ao.tyrlib.packets.BaseClientPacket;

/**
 * @description Client invites a character to join its private group
 * @author Jason
 *
 */
public class PrivateGroupInviteRequestPacket extends BaseClientPacket {

	public static final int TYPE = 50;
	public static final String NAME = "PRIVATE_GROUP_INVITE";
	
	private UserId userId;
	
	public PrivateGroupInviteRequestPacket(DataInputStream input) throws IOException {
		userId = new UserId(input);
	}
	
	public PrivateGroupInviteRequestPacket(long userId) {
		this.userId = new UserId(userId);
	}
	
	public byte[] getBytes() throws IOException {
		return getBytes(userId);
	}
	
	public int getPacketType() {
		return PrivateGroupInviteRequestPacket.TYPE;
	}
	
	public String toString() {
		String output = new StringBuffer()
			.append(TYPE).append(" ").append(NAME).append(" (").append(this.getClass().getName()).append(")")
			.append("\n\tUserId: ").append(userId)
			.toString();
	
		return output;
	}
}
