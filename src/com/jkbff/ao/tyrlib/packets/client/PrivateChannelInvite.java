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
public class PrivateChannelInvite extends BaseClientPacket {

	public static final int TYPE = 50;
	
	private UserId userId;
	
	public PrivateChannelInvite(DataInputStream input) {
		userId = new UserId(input);
	}
	
	public PrivateChannelInvite(long userId) {
		this.userId = new UserId(userId);
	}
	
	public byte[] getBytes() throws IOException {
		return getBytes(userId);
	}
	
	public int getPacketType() {
		return PrivateChannelInvite.TYPE;
	}
	
	public String toString() {
		String output = new StringBuffer()
			.append(TYPE).append(" ").append(this.getClass().getSimpleName())
			.append("\n\tUserId: ").append(userId)
			.toString();
	
		return output;
	}
}
