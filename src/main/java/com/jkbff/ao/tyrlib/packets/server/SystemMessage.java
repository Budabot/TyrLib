package com.jkbff.ao.tyrlib.packets.server;

import com.jkbff.ao.tyrlib.chat.MMDBParser;
import com.jkbff.ao.tyrlib.packets.ExtendedMessage;
import com.jkbff.ao.tyrlib.packets.ExtendedMessageParser;
import sk.sigp.aobot.client.types.Int;
import sk.sigp.aobot.client.types.Text;

import java.io.DataInputStream;
import java.io.IOException;

public class SystemMessage extends BaseServerPacket {

	public static final int TYPE = 37;

	protected final Int clientId;
	protected final Int windowId;
	protected final Int messageId;
	protected final Text messageArgs;

	private static final int CATEGORY_ID = 20000;

	public SystemMessage(DataInputStream input) {
		this.clientId = new Int(input);
		this.windowId = new Int(input);
		this.messageId = new Int(input);
		this.messageArgs = new Text(input);
	}
	
	public SystemMessage(int clientId, int windowId, int messageId, String messageArgs) {
		this.clientId = new Int(clientId);
		this.windowId = new Int(windowId);
		this.messageId = new Int(messageId);
		this.messageArgs = new Text(messageArgs);
	}

	public int getClientId() {
		return clientId.getData();
	}

	public int getWindowId() {
		return windowId.getData();
	}

	public int getMessageId() {
		return messageId.getData();
	}

	public String getMessageArgs() {
		return messageArgs.getData();
	}
	
	public ExtendedMessage getExtendedMessage(MMDBParser mmdbParser) {
		ExtendedMessageParser extendedMessageParser = new ExtendedMessageParser(mmdbParser);

		return extendedMessageParser.parse(CATEGORY_ID, messageId.getData(), messageArgs.toString());
	}

	public int getPacketType() {
		return SystemMessage.TYPE;
	}
	
	public byte[] getBytes() throws IOException {
		return getBytes(clientId, windowId, messageId, messageArgs);
	}
	
	public String toString() {
		String output = new StringBuffer()
			.append(TYPE).append(" ").append(this.getClass().getSimpleName())
			.append("\n\tClientId: ").append(clientId)
			.append("\n\tWindowId: ").append(windowId)
			.append("\n\tMessageId: ").append(messageId)
			.append("\n\tMessageArgs: ").append(messageArgs)
			.toString();

		return output;
	}
}
