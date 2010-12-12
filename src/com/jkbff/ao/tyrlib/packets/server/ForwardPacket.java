package com.jkbff.ao.tyrlib.packets.server;

import java.io.DataInputStream;
import java.io.IOException;

import sk.sigp.aobot.client.types.Map;
import sk.sigp.aobot.client.types.UserId;

import com.jkbff.ao.tyrlib.packets.BaseServerPacket;

public class ForwardPacket extends BaseServerPacket {
	
	public static final int TYPE = 110;
	public static final String NAME = "FORWARD";
	
	private UserId userId;
	private Map map;

	public ForwardPacket(DataInputStream input) throws IOException {
		//this.userId = new UserId(input);
		//this.map = new Map(input);
		throw new RuntimeException(getClass() + " has not been implemented.");
	}
	
	public ForwardPacket(long userId) {
		//this.userId = new UserId(userId);
		throw new RuntimeException(getClass() + " has not been implemented.");
	}
	
	public long getUserId() {
		return this.userId.getLongData();
	}
	
	public int getPacketType() {
		return ClientUnknownPacket.TYPE;
	}
	
	public byte[] getBytes() throws IOException {
		return getBytes(userId, map);
	}
	
	public String toString() {
		String output = new StringBuffer()
			.append(TYPE).append(" ").append(NAME).append(" (").append(this.getClass().getName()).append(")")
			.append("\n\tUserId: ").append(userId)
			.append("\n\tMap: ").append(map)
			.toString();
	
		return output;
	}
}
