package com.jkbff.ao.tyrlib.packets.client;

import java.io.DataInputStream;
import java.io.IOException;

import sk.sigp.aobot.client.types.UserId;
import sk.sigp.aobot.client.types.Text;

import com.jkbff.ao.tyrlib.packets.BaseClientPacket;

/**
 * @description Client sends a message to a private group
 * @author Jason
 *
 */
public class PrivateChannelMessage extends BaseClientPacket {

	public static final int TYPE = 57;
	
	private UserId userId;
	private Text text;
	private Text raw;
	
	public PrivateChannelMessage(DataInputStream input) throws IOException {
		userId = new UserId(input);
		text = new Text(input);
		raw = new Text(input);
	}
	
	public PrivateChannelMessage(int userId, String text, String raw) {
		this.userId = new UserId(userId);
		this.text = new Text(text);
		this.raw = new Text(raw);
	}
	
	public byte[] getBytes() throws IOException {
		return getBytes(userId, text, raw);
	}
	
	public int getPacketType() {
		return PrivateChannelMessage.TYPE;
	}
	
	public String toString() {
		String output = new StringBuffer()
			.append(TYPE).append(" ").append(this.getClass().getSimpleName())
			.append("\n\tUserId: ").append(userId)
			.append("\n\tText: ").append(text)
			.append("\n\tRaw: ").append(raw)
			.toString();
	
		return output;
	}
}
