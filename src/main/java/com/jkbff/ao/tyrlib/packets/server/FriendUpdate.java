package com.jkbff.ao.tyrlib.packets.server;

import java.io.DataInputStream;
import java.io.IOException;

import sk.sigp.aobot.client.types.Int;
import sk.sigp.aobot.client.types.Text;
import sk.sigp.aobot.client.types.CharacterId;

public class FriendUpdate extends BaseServerPacket {

	public static final int TYPE = 40;

	protected final CharacterId charId;
	protected final Int online;
	protected final Text status;
	
	public FriendUpdate(DataInputStream input) {
		this.charId = new CharacterId(input);
		this.online = new Int(input);
		this.status = new Text(input);
	}
	
	public FriendUpdate(long charId, int online, String status) {
		this.charId = new CharacterId(charId);
		this.online = new Int(online);
		this.status = new Text(status);
	}

	public long getCharId() {
		return charId.getData();
	}

	public int getOnline() {
		return online.getData();
	}

	public String getStatus() {
		return status.getData();
	}
	
	public int getPacketType() {
		return FriendUpdate.TYPE;
	}
	
	public byte[] getBytes() throws IOException {
		return getBytes(charId, online, status);
	}
	
	public String toString() {
		String output = new StringBuffer()
			.append(TYPE).append(" ").append(this.getClass().getSimpleName())
			.append("\n\tCharId: ").append(charId)
			.append("\n\tOnline: ").append(online)
			.append("\n\tStatus: ").append(status)
			.toString();
	
		return output;
	}
}
