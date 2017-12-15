package com.jkbff.ao.tyrlib.packets.client;

import java.io.DataInputStream;
import java.io.IOException;

import sk.sigp.aobot.client.types.CharacterId;

/**
 * @description Client accepts an invitation to join a private group
 * @responseTo com.jkbff.ao.tyrlib.packets.server.PrivateGroupJoinRequestPacket
 * @author Jason
 *
 */
public class PrivateChannelAccept extends BaseClientPacket {

	public static final int TYPE = 52;
	
	private CharacterId charId;
	
	public PrivateChannelAccept(DataInputStream input) {
		charId = new CharacterId(input);
	}
	
	public PrivateChannelAccept(long charId) {
		this.charId = new CharacterId(charId);
	}
	
	public long getCharId() {
		return charId.getLongData();
	}
	
	public byte[] getBytes() throws IOException {
		return getBytes(charId);
	}
	
	public int getPacketType() {
		return PrivateChannelAccept.TYPE;
	}
	
	public String toString() {
		String output = new StringBuffer()
			.append(TYPE).append(" ").append(this.getClass().getSimpleName())
			.append("\n\tCharId: ").append(charId)
			.toString();
	
		return output;
	}
}
