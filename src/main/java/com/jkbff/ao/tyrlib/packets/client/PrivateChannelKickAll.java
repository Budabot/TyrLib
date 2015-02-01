package com.jkbff.ao.tyrlib.packets.client;

import java.io.DataInputStream;
import java.io.IOException;

import com.jkbff.ao.tyrlib.packets.BaseClientPacket;

/**
 * @description Client requests all users to be kicked from private group
 * @author Jason
 *
 */
public class PrivateChannelKickAll extends BaseClientPacket {

	public static final int TYPE = 54;
	
	public PrivateChannelKickAll(DataInputStream input) {
		
	}
	
	public PrivateChannelKickAll() {

	}
	
	public byte[] getBytes() throws IOException {
		return new byte[] {};
	}
	
	public int getPacketType() {
		return PrivateChannelKickAll.TYPE;
	}
	
	public String toString() {
		String output = new StringBuffer()
			.append(TYPE).append(" ").append(this.getClass().getSimpleName())
			.toString();
	
		return output;
	}
}
