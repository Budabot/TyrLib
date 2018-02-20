package com.jkbff.ao.tyrlib.packets.client;

import sk.sigp.aobot.client.types.AbstractType;
import sk.sigp.aobot.client.types.Text;
import sk.sigp.aobot.client.types.TextArray;

import java.io.DataInputStream;
import java.io.IOException;

public class ChatCommand extends BaseClientPacket {
	
	public static final int TYPE = 120;

	protected final TextArray command;
	
	public ChatCommand(DataInputStream input) {
		this.command = new TextArray(input);
	}
	
	public ChatCommand(String[] command) {
		this.command = new TextArray(command);
	}
	
	public Text[] getCommand() {
		return command.getData();
	}

	public int getPacketType() {
		return ChatCommand.TYPE;
	}

	@Override
	public AbstractType[] getParameters() {
		return new AbstractType[]{command};
	}
}
