package com.jkbff.ao.tyrlib.packets.client;

import java.io.DataInputStream;
import java.io.IOException;

import sk.sigp.aobot.client.types.CharacterId;

import com.jkbff.ao.tyrlib.packets.BaseClientPacket;

/**
 * @description Client invites a character to join its private group
 * @author Jason
 *
 */
public class PrivateChannelInvite extends BaseClientPacket {

	public static final int TYPE = 50;
	
	private CharacterId charId;
	
	public PrivateChannelInvite(DataInputStream input) {
		charId = new CharacterId(input);
	}
	
	public PrivateChannelInvite(long charId) {
		this.charId = new CharacterId(charId);
	}
	
	public byte[] getBytes() throws IOException {
		return getBytes(charId);
	}
	
	public int getPacketType() {
		return PrivateChannelInvite.TYPE;
	}
	
	public String toString() {
		String output = new StringBuffer()
			.append(TYPE).append(" ").append(this.getClass().getSimpleName())
			.append("\n\tCharId: ").append(charId)
			.toString();
	
		return output;
	}
}
