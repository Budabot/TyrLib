package com.jkbff.ao.tyrlib.packets.client;

import java.io.DataInputStream;
import java.io.IOException;

import sk.sigp.aobot.client.types.CharacterId;
import sk.sigp.aobot.client.types.Text;

public class BuddyAdd extends BaseClientPacket {

	public static final int TYPE = 40;

	protected final CharacterId charId;
	protected final Text status;
	
	public BuddyAdd(DataInputStream input) {
		this.charId = new CharacterId(input);
		this.status = new Text(input);
	}
	
	public BuddyAdd(long charId, String status) {
		this.charId = new CharacterId(charId);
		this.status = new Text(status);
	}
	
	public long getCharId() {
		return charId.getData();
	}

	public String getStatus() {
		return status.getData();
	}
	
	public byte[] getBytes() throws IOException {
		return getBytes(charId, status);
	}
	
	public int getPacketType() {
		return BuddyAdd.TYPE;
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
