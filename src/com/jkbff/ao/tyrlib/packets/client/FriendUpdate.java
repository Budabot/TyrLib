package com.jkbff.ao.tyrlib.packets.client;

import java.io.DataInputStream;
import java.io.IOException;

import sk.sigp.aobot.client.types.Text;
import sk.sigp.aobot.client.types.CharacterId;

import com.jkbff.ao.tyrlib.packets.BaseClientPacket;

public class FriendUpdate extends BaseClientPacket {

	public static final int TYPE = 40;
	
	private CharacterId charId;
	private Text status;
	
	public FriendUpdate(DataInputStream input) {
		this.charId = new CharacterId(input);
		this.status = new Text(input);
	}
	
	public FriendUpdate(long charId, String status) {
		this.charId = new CharacterId(charId);
		this.status = new Text(status);
	}
	
	public long getCharId() {
		return charId.getLongData();
	}

	public String getStatus() {
		return status.getStringData();
	}
	
	public byte[] getBytes() throws IOException {
		return getBytes(charId, status);
	}
	
	public int getPacketType() {
		return FriendUpdate.TYPE;
	}
	
	public String toString() {
		String output = new StringBuffer()
			.append(TYPE).append(" ").append(this.getClass().getSimpleName())
			.append("\n\tCharId: ").append(charId)
			.append("\n\tStatus: ").append(status)
			.toString();
	
		return output;
	}
}
