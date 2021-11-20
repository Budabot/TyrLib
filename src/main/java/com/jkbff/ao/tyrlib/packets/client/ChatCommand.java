package com.jkbff.ao.tyrlib.packets.client;

import sk.sigp.aobot.client.types.AbstractType;
import sk.sigp.aobot.client.types.Int;
import sk.sigp.aobot.client.types.Text;
import sk.sigp.aobot.client.types.TextArray;

import java.io.DataInputStream;
import java.io.IOException;

public class ChatCommand extends BaseClientPacket {
	
	public static final int TYPE = 120;

	protected final TextArray command;
	protected final Int windowId;
	
	public ChatCommand(DataInputStream input) {
		this.command = new TextArray(input);
		this.windowId = new Int(input)
	}
	
	public ChatCommand(String[] command, Int windowId) {
		this.command = new TextArray(command);
		this.windowId = new Int(windowId);
	}
	
	public Text[] getCommand() {
		return command.getData();
	}
	
	public int getWindowId() {
		return windowId.getData();
	}

	public int getPacketType() {
		return ChatCommand.TYPE;
	}

	@Override
	public AbstractType[] getParameters() {
		return new AbstractType[]{command, windowId};
	}
}
