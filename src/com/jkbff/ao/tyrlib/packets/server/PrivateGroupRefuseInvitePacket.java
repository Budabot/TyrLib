package com.jkbff.ao.tyrlib.packets.server;

import java.io.DataInputStream;
import java.io.IOException;

import sk.sigp.aobot.client.types.UserId;

import com.jkbff.ao.tyrlib.packets.BaseServerPacket;

public class PrivateGroupRefuseInvitePacket extends BaseServerPacket {

	public static final int TYPE = 58;
	
	private UserId userId1;
	private UserId userId2;

	public PrivateGroupRefuseInvitePacket(DataInputStream input) {
		this.userId1 = new UserId(input);
		this.userId2 = new UserId(input);
	}
	
	public PrivateGroupRefuseInvitePacket(long userId1, long userId2) {
		this.userId1 = new UserId(userId1);
		this.userId2 = new UserId(userId2);
	}
	
	public long getUserId1() {
		return this.userId1.getLongData();
	}
	
	public long getUserId2() {
		return this.userId2.getLongData();
	}
	
	public int getPacketType() {
		return PrivateGroupRefuseInvitePacket.TYPE;
	}
	
	public byte[] getBytes() throws IOException {
		return getBytes(userId1, userId2);
	}
	
	public String toString() {
		String output = new StringBuffer()
			.append(TYPE).append(" ").append(this.getClass().getSimpleName())
			.append("\n\tUserId1: ").append(userId1)
			.append("\n\tUserId2: ").append(userId2)
			.toString();
	
		return output;
	}
}