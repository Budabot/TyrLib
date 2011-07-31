package com.jkbff.ao.tyrlib.packets.client;

import java.io.DataInputStream;
import java.io.IOException;

import sk.sigp.aobot.client.types.CharacterId;

import com.jkbff.ao.tyrlib.packets.BaseClientPacket;

/**
 * @description Client requests a user to be kicked from private group
 * @author Jason
 *
 */
public class PrivateChannelKick extends BaseClientPacket {

	public static final int TYPE = 51;
	
	private CharacterId charId;
	
	public PrivateChannelKick(DataInputStream input) {
		charId = new CharacterId(input);
	}
	
	public PrivateChannelKick(long charId) {
		this.charId = new CharacterId(charId);
	}
	
	public byte[] getBytes() throws IOException {
		return getBytes(charId);
	}
	
	public int getPacketType() {
		return PrivateChannelKick.TYPE;
	}
	
	public String toString() {
		String output = new StringBuffer()
			.append(TYPE).append(" ").append(this.getClass().getSimpleName())
			.append("\n\tCharId: ").append(charId)
			.toString();
	
		return output;
	}
}
