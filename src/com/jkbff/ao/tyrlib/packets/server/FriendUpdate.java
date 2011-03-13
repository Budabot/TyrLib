package com.jkbff.ao.tyrlib.packets.server;

import java.io.DataInputStream;
import java.io.IOException;

import sk.sigp.aobot.client.types.Int;
import sk.sigp.aobot.client.types.Text;
import sk.sigp.aobot.client.types.UserId;

import com.jkbff.ao.tyrlib.packets.BaseServerPacket;

public class FriendUpdate extends BaseServerPacket {

	public static final int TYPE = 40;
	
	private UserId userId;
	private Int online;
	private Text status;
	
	public FriendUpdate(DataInputStream input) throws IOException {
		this.userId = new UserId(input);
		this.online = new Int(input);
		this.status = new Text(input);
	}
	
	public FriendUpdate(long userId, int online, String status) {
		this.userId = new UserId(userId);
		this.online = new Int(online);
		this.status = new Text(status);
	}

	public long getUserId() {
		return userId.getLongData();
	}

	public int getOnline() {
		return online.getIntData();
	}

	public String getStatus() {
		return status.getStringData();
	}
	
	public int getPacketType() {
		return FriendUpdate.TYPE;
	}
	
	public byte[] getBytes() throws IOException {
		return getBytes(userId, online, status);
	}
	
	public String toString() {
		String output = new StringBuffer()
			.append(TYPE).append(" ").append(this.getClass().getSimpleName())
			.append("\n\tUserId: ").append(userId)
			.append("\n\tOnline: ").append(online)
			.append("\n\tStatus: ").append(status)
			.toString();
	
		return output;
	}
}
