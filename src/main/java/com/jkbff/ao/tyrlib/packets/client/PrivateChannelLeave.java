package com.jkbff.ao.tyrlib.packets.client;

import java.io.DataInputStream;
import java.io.IOException;

import sk.sigp.aobot.client.types.CharacterId;

/**
 * @description Purpose unknown
 * @author Jason
 *
 */
public class PrivateChannelLeave extends BaseClientPacket {

	public static final int TYPE = 53;

	protected final CharacterId charId;
	
	public PrivateChannelLeave(DataInputStream input) {
		charId = new CharacterId(input);
	}
	
	public PrivateChannelLeave(long charId) {
		this.charId = new CharacterId(charId);
	}
	
	public long getCharId() {
		return charId.getData();
	}
	
	public byte[] getBytes() throws IOException {
		return getBytes(charId);
	}
	
	public int getPacketType() {
		return PrivateChannelLeave.TYPE;
	}
	
	public String toString() {
		String output = new StringBuffer()
			.append(TYPE).append(" ").append(this.getClass().getSimpleName())
			.append("\n\tCharId: ").append(charId)
			.toString();
	
		return output;
	}
}
