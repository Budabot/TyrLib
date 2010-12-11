package com.jkbff.ao.tyrlib.packets.server;

import java.io.DataInputStream;
import java.io.IOException;

import sk.sigp.aobot.client.types.UserId;

import com.jkbff.ao.tyrlib.packets.BaseServerPacket;

public class PrivateGroupInvitedPacket extends BaseServerPacket {

	public static final int TYPE = 50;
	public static final String NAME = "PRIVGRP_INVITE";
	
	private UserId userId;

	public PrivateGroupInvitedPacket(DataInputStream input) throws IOException {
		this.userId = new UserId(input);
	}
	
	public PrivateGroupInvitedPacket(long userId) {
		this.userId = new UserId(userId);
	}
	
	public long getUserId() {
		return this.userId.getLongData();
	}
	
	public int getPacketType() {
		return PrivateGroupInvitedPacket.TYPE;
	}
	
	public byte[] getBytes() throws IOException {
		return getBytes(userId);
	}
}
