package com.jkbff.ao.tyrlib.packets.client;

import java.io.DataInputStream;
import java.io.IOException;

import sk.sigp.aobot.client.types.CharacterId;
import sk.sigp.aobot.client.types.Text;

import com.jkbff.ao.tyrlib.packets.BaseClientPacket;

/**
 * @description Client sends a message to a private group
 * @author Jason
 *
 */
public class PrivateChannelMessage extends BaseClientPacket {

	public static final int TYPE = 57;
	
	private CharacterId charId;
	private Text text;
	private Text raw;
	
	public PrivateChannelMessage(DataInputStream input) {
		charId = new CharacterId(input);
		text = new Text(input);
		raw = new Text(input);
	}
	
	public PrivateChannelMessage(int charId, String text, String raw) {
		this.charId = new CharacterId(charId);
		this.text = new Text(text);
		this.raw = new Text(raw);
	}
	
	public byte[] getBytes() throws IOException {
		return getBytes(charId, text, raw);
	}
	
	public int getPacketType() {
		return PrivateChannelMessage.TYPE;
	}
	
	public String toString() {
		String output = new StringBuffer()
			.append(TYPE).append(" ").append(this.getClass().getSimpleName())
			.append("\n\tCharId: ").append(charId)
			.append("\n\tText: ").append(text)
			.append("\n\tRaw: ").append(raw)
			.toString();
	
		return output;
	}
}
