package com.jkbff.ao.tyrlib.packets.client;

import java.io.DataInputStream;
import java.io.IOException;

import sk.sigp.aobot.client.types.Raw;
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
	private Raw raw;
	
	public PrivateMessageSend(DataInputStream input) throws IOException {
		userId = new UserId(input);
		text = new Text(input);
		raw = new Raw(input);
	}
	
	public PrivateMessageSend(long userId, String text, String raw) {
		this.userId = new UserId(userId);
		this.text = new Text(text);
		this.raw = new Raw(raw);
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
