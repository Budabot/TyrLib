package com.jkbff.ao.tyrlib.packets.client;

import java.io.DataInputStream;
import java.io.IOException;

import sk.sigp.aobot.client.types.Text;
import sk.sigp.aobot.client.types.UserId;

import com.jkbff.ao.tyrlib.packets.BaseClientPacket;

/**
 * @description Client sends private message. 
 * @author Jason
 *
 */
public class PrivateMessageSend extends BaseClientPacket {

	public static final int TYPE = 30;
	
	private UserId userId;
	private Text text;
	private Text raw;
	
	public PrivateMessageSend(DataInputStream input) {
		userId = new UserId(input);
		text = new Text(input);
		raw = new Text(input);
	}
	
	public PrivateMessageSend(long userId, String text, String raw) {
		this.userId = new UserId(userId);
		this.text = new Text(text);
		this.raw = new Text(raw);
	}
	
	public byte[] getBytes() throws IOException {
		return getBytes(userId, text, raw);
	}
	
	public int getPacketType() {
		return PrivateMessageSend.TYPE;
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
