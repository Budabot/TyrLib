package com.jkbff.ao.tyrlib.packets.server;

import java.io.DataInputStream;
import java.io.IOException;

import sk.sigp.aobot.client.types.Text;
import sk.sigp.aobot.client.types.TextBlob;
import sk.sigp.aobot.client.types.CharacterId;

import com.jkbff.ao.tyrlib.packets.BaseServerPacket;

public class VicinityMessage extends BaseServerPacket {

	public static final int TYPE = 34;
	
	private CharacterId charId;
	private Text message;
	private TextBlob blob;

	public VicinityMessage(DataInputStream input) {
		this.charId = new CharacterId(input);
		this.message = new Text(input);
		this.blob = new TextBlob(input);
	}
	
	public VicinityMessage(long charId, String message, String blob) {
		this.charId = new CharacterId(charId);
		this.message = new Text(message);
		this.blob = new TextBlob(blob);
	}
	
	public long getCharId() {
		return this.charId.getLongData();
	}
	
	public String getMessage() {
		return this.message.getStringData();
	}
	
	public String getBlob() {
		return this.blob.getStringData();
	}
	
	public int getPacketType() {
		return VicinityMessage.TYPE;
	}
	
	public byte[] getBytes() throws IOException {
		return getBytes(charId, message, blob);
	}
	
	@Override
	public String toString() {
		String output = new StringBuffer()
			.append(TYPE).append(" ").append(this.getClass().getSimpleName())
			.append("\n\tCharId: ").append(charId)
			.append("\n\tMessage: ").append(message)
			.append("\n\tBlob: ").append(blob)
			.toString();
	
		return output;
	}
}
