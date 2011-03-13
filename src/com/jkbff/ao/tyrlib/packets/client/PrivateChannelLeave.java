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
public class PrivateChannelLeave extends BaseClientPacket {

	public static final int TYPE = 53;
	
	private UserId userId;
	
	public PrivateChannelLeave(DataInputStream input) {
		userId = new UserId(input);
	}
	
	public PrivateChannelLeave(long userId) {
		this.userId = new UserId(userId);
	}
	
	public byte[] getBytes() throws IOException {
		return getBytes(userId);
	}
	
	public int getPacketType() {
		return PrivateChannelLeave.TYPE;
	}
	
	public String toString() {
		String output = new StringBuffer()
			.append(TYPE).append(" ").append(this.getClass().getSimpleName())
			.append("\n\tUserId: ").append(userId)
			.toString();
	
		return output;
	}
}
