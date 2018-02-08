package com.jkbff.ao.tyrlib.packets.server;

import java.io.DataInputStream;
import java.io.IOException;

import sk.sigp.aobot.client.types.Text;
import sk.sigp.aobot.client.types.CharacterId;

public class PrivateChannelMessage extends BaseServerPacket {

	public static final int TYPE = 57;

	protected final CharacterId privateChannelId;
	protected final CharacterId charId;
	protected final Text message;
	protected final Text raw;

	public PrivateChannelMessage(DataInputStream input) {
		this.privateChannelId = new CharacterId(input);
		this.charId = new CharacterId(input);
		this.message = new Text(input);
		this.raw = new Text(input);
	}
	
	public PrivateChannelMessage(long privateChannelId, long charId, String message, String raw) {
		this.privateChannelId = new CharacterId(privateChannelId);
		this.charId = new CharacterId(charId);
		this.message = new Text(message);
		this.raw = new Text(raw);
	}
	
	public long getPrivateChannelId() {
		return this.privateChannelId.getData();
	}
	
	public long getCharId() {
		return this.charId.getData();
	}
	
	public String getMessage() {
		return this.message.getData();
	}
	
	public String getRaw() {
		return this.raw.getData();
	}
	
	public int getPacketType() {
		return PrivateChannelMessage.TYPE;
	}
	
	public byte[] getBytes() throws IOException {
		return getBytes(privateChannelId, charId, message, raw);
	}
	
	public String toString() {
		String output = new StringBuffer()
			.append(TYPE).append(" ").append(this.getClass().getSimpleName())
			.append("\n\tPrivateChannelId: ").append(privateChannelId)
			.append("\n\tCharId: ").append(charId)
			.append("\n\tMessage: ").append(message)
			.append("\n\tRaw: ").append(raw)
			.toString();
	
		return output;
	}
}
