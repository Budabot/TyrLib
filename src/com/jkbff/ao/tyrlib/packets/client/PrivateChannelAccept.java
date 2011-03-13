package com.jkbff.ao.tyrlib.packets.client;

import java.io.DataInputStream;
import java.io.IOException;

import sk.sigp.aobot.client.types.UserId;

import com.jkbff.ao.tyrlib.packets.BaseClientPacket;

/**
 * @description Client accepts an invitation to join a private group
 * @responseTo com.jkbff.ao.tyrlib.packets.server.PrivateGroupJoinRequestPacket
 * @author Jason
 *
 */
public class PrivateChannelAccept extends BaseClientPacket {

	public static final int TYPE = 52;
	
	private UserId userId;
	
	public PrivateChannelAccept(DataInputStream input) throws IOException {
		userId = new UserId(input);
	}
	
	public PrivateChannelAccept(long userId) {
		this.userId = new UserId(userId);
	}
	
	public byte[] getBytes() throws IOException {
		return getBytes(userId);
	}
	
	public int getPacketType() {
		return PrivateChannelAccept.TYPE;
	}
	
	public String toString() {
		String output = new StringBuffer()
			.append(TYPE).append(" ").append(this.getClass().getSimpleName())
			.append("\n\tUserId: ").append(userId)
			.toString();
	
		return output;
	}
}
