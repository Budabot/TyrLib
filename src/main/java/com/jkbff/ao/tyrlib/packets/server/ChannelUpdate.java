package com.jkbff.ao.tyrlib.packets.server;

import java.io.DataInputStream;
import java.io.IOException;

import sk.sigp.aobot.client.types.ChatGroupId;
import sk.sigp.aobot.client.types.Int;
import sk.sigp.aobot.client.types.Text;

/**
 * @description Announces to the client what chat channels are available.
 * @author Jason
 *
 */
public class ChannelUpdate extends BaseServerPacket {

	public static final int TYPE = 60;
	
	private ChatGroupId chatGroupId;
	private Text groupName;
	private Int unknownInt;
	private Text flags;

	public ChannelUpdate(DataInputStream input) {
		this.chatGroupId = new ChatGroupId(input);
		this.groupName = new Text(input);
		this.unknownInt = new Int(input);
		this.flags = new Text(input);
	}
	
	public ChannelUpdate(int channelType, int channelId, String groupName, int unknownInt, String flags) {
		this.chatGroupId = new ChatGroupId(channelId);
		this.groupName = new Text(groupName);
		this.unknownInt = new Int(unknownInt);
		this.flags = new Text(flags);
	}
	
	public long getChatGroupId() {
		return chatGroupId.getLongData();
	}

	public String getGroupName() {
		return groupName.getStringData();
	}

	public String getFlags() {
		return flags.getStringData();
	}

	public int getUnknownInt() {
		return unknownInt.getIntData();
	}
	
	public int getPacketType() {
		return ChannelUpdate.TYPE;
	}
	
	public byte[] getBytes() throws IOException {
		return getBytes(chatGroupId, groupName, unknownInt, flags);
	}
	
	public long getGuildId() {
		if (chatGroupId.isGuildChannel()) {
			return getChatGroupId() & 0xFFFFFFFFL;
		}
		return 0;
	}
	
	public String toString() {
		String output = new StringBuffer()
			.append(TYPE).append(" ").append(this.getClass().getSimpleName())
			.append("\n\tChatGroupId: ").append(chatGroupId)
			.append("\n\tGroupName: ").append(groupName)
			.append("\n\tUnknownInt: ").append(unknownInt)
			.append("\n\tMute: ").append(flags)
			.toString();
		
		return output;
	}
}
