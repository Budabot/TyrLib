package com.jkbff.ao.tyrlib.packets.client;

import java.io.DataInputStream;
import java.io.IOException;

import sk.sigp.aobot.client.types.CharacterId;
import sk.sigp.aobot.client.types.Text;

/**
 * @description Client sends private message. 
 * @author Jason
 *
 */
public class PrivateMessageSend extends BaseClientPacket {

	public static final int TYPE = 30;
	
	private CharacterId charId;
	private Text message;
	private Text raw;
	
	public PrivateMessageSend(DataInputStream input) {
		charId = new CharacterId(input);
		message = new Text(input);
		raw = new Text(input);
	}
	
	public PrivateMessageSend(long charId, String text, String raw) {
		this.charId = new CharacterId(charId);
		this.message = new Text(text);
		this.raw = new Text(raw);
	}
	
	public long getCharId() {
		return charId.getData();
	}
	
	public String getMessage() {
		return message.getData();
	}
	
	public String getRaw() {
		return raw.getData();
	}
	
	public byte[] getBytes() throws IOException {
		return getBytes(charId, message, raw);
	}
	
	public int getPacketType() {
		return PrivateMessageSend.TYPE;
	}
	
	public String toString() {
		String output = new StringBuffer()
			.append(TYPE).append(" ").append(this.getClass().getSimpleName())
			.append("\n\tCharId: ").append(charId)
			.append("\n\tMessage: ").append(message)
			.append("\n\tRaw: ").append(raw)
			.toString();
	
		return output;
	}
}
