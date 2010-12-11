package com.jkbff.ao.tyrlib.packets.server;

import java.io.DataInputStream;
import java.io.IOException;

import sk.sigp.aobot.client.types.UserId;

import com.jkbff.ao.tyrlib.packets.BaseServerPacket;

public class PrivateGroupPartPacket extends BaseServerPacket {

	public static final int TYPE = 53;
	public static final String NAME = "PRIVGRP_PART";
	
	private UserId userId;

	public PrivateGroupPartPacket(DataInputStream input) throws IOException {
		this.userId = new UserId(input);
	}
	
	public PrivateGroupPartPacket(int userId) {
		this.userId = new UserId(userId);
	}
	
	public long getUserId() {
		return this.userId.getLongData();
	}
	
	public int getPacketType() {
		return PrivateGroupPartPacket.TYPE;
	}
	
	public byte[] getBytes() throws IOException {
		return getBytes(userId);
	}
}
