package com.jkbff.ao.tyrlib.packets.client;

import java.io.DataInputStream;
import java.io.IOException;

import sk.sigp.aobot.client.types.UserId;

import com.jkbff.ao.tyrlib.packets.BaseClientPacket;

public class FriendRemove extends BaseClientPacket {

	public static final int TYPE = 41;
	
	private UserId userId;
	
	public FriendRemove(DataInputStream input) {
		this.userId = new UserId(input);
	}
	
	public FriendRemove(long userId) {
		this.userId = new UserId(userId);
	}
	
	public long getUserId() {
		return userId.getLongData();
	}
	
	public byte[] getBytes() throws IOException {
		return getBytes(userId);
	}
	
	public int getPacketType() {
		return FriendRemove.TYPE;
	}
	
	public String toString() {
		String output = new StringBuffer()
			.append(TYPE).append(" ").append(this.getClass().getSimpleName())
			.append("\n\tUserId: ").append(userId)
			.toString();
	
		return output;
	}
}
