package com.jkbff.ao.tyrlib.packets.server;

import java.io.DataInputStream;
import java.io.IOException;

import sk.sigp.aobot.client.types.ChannelId;
import sk.sigp.aobot.client.types.Int;
import sk.sigp.aobot.client.types.Text;

/**
 * @description Announces to the client what chat channels are available.
 * @author Jason
 *
 */
public class PublicChannelJoined extends BaseServerPacket {

	public static final int TYPE = 60;

	protected final ChannelId channelId;
	protected final Text groupName;
	protected final Int unknownInt;
	protected final Text flags;

	public PublicChannelJoined(DataInputStream input) {
		this.channelId = new ChannelId(input);
		this.groupName = new Text(input);
		this.unknownInt = new Int(input);
		this.flags = new Text(input);
	}
	
	public PublicChannelJoined(long channelId, String groupName, int unknownInt, String flags) {
		this.channelId = new ChannelId(channelId);
		this.groupName = new Text(groupName);
		this.unknownInt = new Int(unknownInt);
		this.flags = new Text(flags);
	}
	
	public long getChannelId() {
		return channelId.getData();
	}

	public String getGroupName() {
		return groupName.getData();
	}

	public String getFlags() {
		return flags.getData();
	}

	public int getUnknownInt() {
		return unknownInt.getData();
	}
	
	public int getPacketType() {
		return PublicChannelJoined.TYPE;
	}
	
	public byte[] getBytes() throws IOException {
		return getBytes(channelId, groupName, unknownInt, flags);
	}

	public String toString() {
		String output = new StringBuffer()
			.append(TYPE).append(" ").append(this.getClass().getSimpleName())
			.append("\n\tChannelId: ").append(channelId)
			.append("\n\tGroupName: ").append(groupName)
			.append("\n\tUnknownInt: ").append(unknownInt)
			.append("\n\tMute: ").append(flags)
			.toString();
		
		return output;
	}
}
