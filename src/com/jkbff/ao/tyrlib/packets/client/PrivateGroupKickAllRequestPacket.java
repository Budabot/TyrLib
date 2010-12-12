package com.jkbff.ao.tyrlib.packets.client;

import java.io.DataInputStream;
import java.io.IOException;

import com.jkbff.ao.tyrlib.packets.BaseClientPacket;

/**
 * @description Client requests all users to be kicked from private group
 * @author Jason
 *
 */
public class PrivateGroupKickAllRequestPacket extends BaseClientPacket {

	public static final int TYPE = 54;
	public static final String NAME = "PRIVATE_GROUP_MESSAGE";
	
	public PrivateGroupKickAllRequestPacket(DataInputStream input) throws IOException {
		
	}
	
	public PrivateGroupKickAllRequestPacket() {

	}
	
	public byte[] getBytes() throws IOException {
		return getBytes();
	}
	
	public int getPacketType() {
		return PrivateGroupKickAllRequestPacket.TYPE;
	}
	
	public String toString() {
		String output = new StringBuffer()
			.append(TYPE).append(" ").append(NAME).append(" (").append(this.getClass().getName()).append(")")
			.toString();
	
		return output;
	}
}
