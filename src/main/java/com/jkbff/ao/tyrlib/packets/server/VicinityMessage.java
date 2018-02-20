package com.jkbff.ao.tyrlib.packets.server;

import java.io.DataInputStream;
import java.io.IOException;

import sk.sigp.aobot.client.types.AbstractType;
import sk.sigp.aobot.client.types.Text;
import sk.sigp.aobot.client.types.TextBlob;
import sk.sigp.aobot.client.types.CharacterId;

public class VicinityMessage extends BaseServerPacket {

	public static final int TYPE = 34;

	protected final CharacterId charId;
	protected final Text message;
	protected final TextBlob blob;

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
		return this.charId.getData();
	}
	
	public String getMessage() {
		return this.message.getData();
	}
	
	public String getBlob() {
		return this.blob.getData();
	}
	
	public int getPacketType() {
		return VicinityMessage.TYPE;
	}

	@Override
	public AbstractType[] getParameters() {
		return new AbstractType[]{charId, message, blob};
	}
}
