package com.jkbff.ao.tyrlib.packets.server;

import java.io.DataInputStream;
import java.io.IOException;

import sk.sigp.aobot.client.types.Int;
import sk.sigp.aobot.client.types.Text;

import com.jkbff.ao.tyrlib.packets.BaseServerPacket;
import com.jkbff.ao.tyrlib.packets.ExtendedMessage;

public class SystemMessage extends BaseServerPacket {

	public static final int TYPE = 37;
	
	private Int clientId;
	private Int windowId;
	private Int messageId;
	private Text messageArgs;
	private ExtendedMessage extendedMessage;
	
	private static final int CATEGORY_ID = 20000;

	public SystemMessage(DataInputStream input) throws IOException {
		this.clientId = new Int(input);
		this.windowId = new Int(input);
		this.messageId = new Int(input);
		this.messageArgs = new Text(input);

		extendedMessage = new ExtendedMessage(CATEGORY_ID, messageId.getIntData(), messageArgs.getStringData());
	}
	
	public SystemMessage(int clientId, int windowId, int messageId, String messageArgs) {
		this.clientId = new Int(clientId);
		this.windowId = new Int(windowId);
		this.messageId = new Int(messageId);
		this.messageArgs = new Text(messageArgs);
		
		extendedMessage = new ExtendedMessage(CATEGORY_ID, messageId, messageArgs);
	}
	
	public SystemMessage(int clientId, int windowId, ExtendedMessage extendedMessage) {
		this.clientId = new Int(clientId);
		this.windowId = new Int(windowId);
		this.messageId = new Int((int) extendedMessage.getInstanceId());
		this.messageArgs = new Text(extendedMessage.getMessage());
		
		this.extendedMessage = extendedMessage;
	}

	public int getClientId() {
		return clientId.getIntData();
	}

	public int getWindowId() {
		return windowId.getIntData();
	}

	public int getMessageId() {
		return messageId.getIntData();
	}

	public String getMessageArgs() {
		return messageArgs.getStringData();
	}
	
	public ExtendedMessage getExtendedMessage() {
		return extendedMessage;
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
			.append("\n\tExtendedMessage: ").append(extendedMessage)
			.toString();

		return output;
	}
}
