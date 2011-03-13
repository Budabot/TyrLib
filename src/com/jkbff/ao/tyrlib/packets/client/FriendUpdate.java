package com.jkbff.ao.tyrlib.packets.client;

import java.io.DataInputStream;
import java.io.IOException;

import sk.sigp.aobot.client.types.Text;
import sk.sigp.aobot.client.types.UserId;

import com.jkbff.ao.tyrlib.packets.BaseClientPacket;

public class FriendUpdate extends BaseClientPacket {

	public static final int TYPE = 40;
	
	private UserId userId;
	private Text status;
	
	public FriendUpdate(DataInputStream input) {
		this.userId = new UserId(input);
		this.status = new Text(input);
	}
	
	public FriendUpdate(long userId, String status) {
		this.userId = new UserId(userId);
		this.status = new Text(status);
	}
	
	public long getUserId() {
		return userId.getLongData();
	}

	public String getStatus() {
		return status.getStringData();
	}
	
	public byte[] getBytes() throws IOException {
		return getBytes(userId, status);
	}
	
	public int getPacketType() {
		return FriendUpdate.TYPE;
	}
	
	public String toString() {
		String output = new StringBuffer()
			.append(TYPE).append(" ").append(this.getClass().getSimpleName())
			.append("\n\tUserId: ").append(userId)
			.append("\n\tStatus: ").append(status)
			.toString();
	
		return output;
	}
}
