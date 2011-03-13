package com.jkbff.ao.tyrlib.packets.client;

import java.io.DataInputStream;
import java.io.IOException;

import sk.sigp.aobot.client.types.Int;

import com.jkbff.ao.tyrlib.packets.BaseClientPacket;

/**
 * @description Client sets online status.  Not sure how to use this.
 * @author Jason
 *
 */
public class OnlineStatusSetPacket extends BaseClientPacket {

	public static final int TYPE = 42;
	
	private Int status;
	
	public OnlineStatusSetPacket(DataInputStream input) throws IOException {
		status = new Int(input);
	}
	
	public OnlineStatusSetPacket(int status) {
		this.status = new Int(status);
	}
	
	public byte[] getBytes() throws IOException {
		return getBytes(status);
	}
	
	public int getPacketType() {
		return OnlineStatusSetPacket.TYPE;
	}
	
	public String toString() {
		String output = new StringBuffer()
			.append(TYPE).append(" ").append(this.getClass().getSimpleName())
			.append("\n\tStatus: ").append(status)
			.toString();
	
		return output;
	}
}
