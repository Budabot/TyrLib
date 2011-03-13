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
public class PrivateChannelKick extends BaseClientPacket {

	public static final int TYPE = 51;
	
	private UserId userId;
	
	public PrivateChannelKick(DataInputStream input) throws IOException {
		userId = new UserId(input);
	}
	
	public PrivateChannelKick(long userId) {
		this.userId = new UserId(userId);
	}
	
	public byte[] getBytes() throws IOException {
		return getBytes(userId);
	}
	
	public int getPacketType() {
		return PrivateChannelKick.TYPE;
	}
	
	public String toString() {
		String output = new StringBuffer()
			.append(TYPE).append(" ").append(this.getClass().getSimpleName())
			.append("\n\tUserId: ").append(userId)
			.toString();
	
		return output;
	}
}
