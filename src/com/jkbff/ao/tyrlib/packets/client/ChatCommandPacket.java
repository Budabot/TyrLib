package com.jkbff.ao.tyrlib.packets.client;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import sk.sigp.aobot.client.types.Text;

import com.jkbff.ao.tyrlib.packets.BaseClientPacket;

public class ChatCommandPacket extends BaseClientPacket {
	
	public static final int TYPE = 120;
	public static final String NAME = "AOCP_CHAT_COMMAND";
	
	private Text[] command;
	
	public ChatCommandPacket(DataInputStream input) throws IOException {
		List<Text> texts = new ArrayList<Text>();
		while (input.available() > 0) {
			texts.add(new Text(input));
		}
		command = texts.toArray(new Text[]{});
	}
	
	public ChatCommandPacket(String[] command) {
		this.command = new Text[command.length];
		
		for (int i = 0; i < command.length; i++) {
			this.command[i] = new Text(command[i]);
		}
	}
	
	public byte[] getBytes() throws IOException {
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		DataOutputStream outputStream = new DataOutputStream(byteStream);
		
		// write packet type
		outputStream.writeShort(getPacketType());
		
		// write packet payload length
		// +2 accounts for the 2 bytes that indicate the size of each array
		int length = command.length + 2;
		outputStream.writeShort(length);
		
		// write payload
		
		// write array length
		outputStream.writeShort(command.length);
		for (int i = 0; i < command.length; i++) {
			outputStream.write(command[i].getRaw());
		}
		
		return byteStream.toByteArray();
	}
	
	public int getPacketType() {
		return ChatCommandPacket.TYPE;
	}
	
	public String toString() {
		StringBuffer output = new StringBuffer()
			.append(TYPE).append(" ").append(NAME).append(" (").append(this.getClass().getName()).append(")");
		
		int i = 0;
		for (Text text: command) {
			output.append("\n\tCommand #").append(i++).append(": ").append(text);
		}
		
		return output.toString();
	}
}
