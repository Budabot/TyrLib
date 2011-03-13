package com.jkbff.ao.tyrlib.packets.server;

import java.io.DataInputStream;
import java.io.IOException;

import sk.sigp.aobot.client.types.UserId;

import com.jkbff.ao.tyrlib.packets.BaseServerPacket;

public class FriendRemove extends BaseServerPacket {

	public static final int TYPE = 41;
	
	private UserId userId;

	public FriendRemove(DataInputStream input) throws IOException {
		this.userId = new UserId(input);
	}
	
	public FriendRemove(long userId) {
		this.userId = new UserId(userId);
	}
	
	public long getUserId() {
		return this.userId.getLongData();
	}
	
	public int getPacketType() {
		return FriendRemove.TYPE;
	}
	
	public byte[] getBytes() throws IOException {
		return getBytes(userId);
	}
	
	public String toString() {
		String output = new StringBuffer()
			.append(TYPE).append(" ").append(this.getClass().getSimpleName())
			.append("\n\tUserId: ").append(userId)
			.toString();
	
		return output;
	}
}
