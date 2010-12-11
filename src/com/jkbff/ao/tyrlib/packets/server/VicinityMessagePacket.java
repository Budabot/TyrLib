package com.jkbff.ao.tyrlib.packets.server;

import java.io.DataInputStream;
import java.io.IOException;

import sk.sigp.aobot.client.types.Text;
import sk.sigp.aobot.client.types.TextBlob;
import sk.sigp.aobot.client.types.UserId;

import com.jkbff.ao.tyrlib.packets.BaseServerPacket;

public class VicinityMessagePacket extends BaseServerPacket {

	public static final int TYPE = 34;
	public static final String NAME = "MSG_VICINITY";
	
	private UserId userId;
	private Text message;
	private TextBlob blob;

	public VicinityMessagePacket(DataInputStream input) throws IOException {
		this.userId = new UserId(input);
		this.message = new Text(input);
		this.blob = new TextBlob(input);
	}
	
	public VicinityMessagePacket(long userId, String message, String blob) {
		this.userId = new UserId(userId);
		this.message = new Text(message);
		this.blob = new TextBlob(blob);
	}
	
	public long getUserId() {
		return this.userId.getLongData();
	}
	
	public String getMessage() {
		return this.message.getStringData();
	}
	
	public String getBlob() {
		return this.blob.getStringData();
	}
	
	public int getPacketType() {
		return VicinityMessagePacket.TYPE;
	}
	
	public byte[] getBytes() throws IOException {
		
		return getBytes(userId, message, blob);
	}
}
