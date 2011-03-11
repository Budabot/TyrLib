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
public class OutgoingPrivateMessagePacket extends BaseClientPacket {

	public static final int TYPE = 30;
	public static final String NAME = "AOCP_MSG_PRIVATE";
	
	private UserId userId;
	private Text text;
	private Text voice;
	
	public OutgoingPrivateMessagePacket(DataInputStream input) throws IOException {
		userId = new UserId(input);
		text = new Text(input);
		voice = new Text(input);
	}
	
	public OutgoingPrivateMessagePacket(long userId, String text, String voice) {
		this.userId = new UserId(userId);
		this.text = new Text(text);
		this.voice = new Text(voice);
	}
	
	public byte[] getBytes() throws IOException {
		return getBytes(userId, text, voice);
	}
	
	public int getPacketType() {
		return OutgoingPrivateMessagePacket.TYPE;
	}
	
	public String toString() {
		String output = new StringBuffer()
			.append(TYPE).append(" ").append(NAME).append(" (").append(this.getClass().getName()).append(")")
			.append("\n\tUserId: ").append(userId)
			.append("\n\tText: ").append(text)
			.append("\n\tVoice: ").append(voice)
			.toString();
	
		return output;
	}
}
