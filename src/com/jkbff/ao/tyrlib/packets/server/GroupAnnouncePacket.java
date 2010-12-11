package com.jkbff.ao.tyrlib.packets.server;

import java.io.DataInputStream;
import java.io.IOException;

import sk.sigp.aobot.client.types.ChatGroupId;
import sk.sigp.aobot.client.types.Int;
import sk.sigp.aobot.client.types.Raw;
import sk.sigp.aobot.client.types.Text;

import com.jkbff.ao.tyrlib.packets.BaseServerPacket;

/**
 * @description Announces to the client what chat channels are available.
 * @author Jason
 *
 */
public class GroupAnnouncePacket extends BaseServerPacket {

	public static final int TYPE = 60;
	public static final String NAME = "GROUP_ANNOUNCE";
	
	private ChatGroupId chatGroupId;
	private Text groupName;
	private Int unknownInt;
	private Raw mute;

	public GroupAnnouncePacket(DataInputStream input) throws IOException {
		this.chatGroupId = new ChatGroupId(input);
		this.groupName = new Text(input);
		this.unknownInt = new Int(input);
		this.mute = new Raw(input);
	}
	
	public GroupAnnouncePacket(int channelType, int channelId, String groupName, int unknownInt, String mute) {
		this.chatGroupId = new ChatGroupId(channelId);
		this.groupName = new Text(groupName);
		this.unknownInt = new Int(unknownInt);
		this.mute = new Raw(mute);
	}
	
	public long getChatGroupId() {
		return chatGroupId.getLongData();
	}

	public String getGroupName() {
		return groupName.getStringData();
	}

	public String getMute() {
		return mute.getStringData();
	}

	public int getUnknownInt() {
		return unknownInt.getIntData();
	}
	
	public int getPacketType() {
		return GroupAnnouncePacket.TYPE;
	}
	
	public byte[] getBytes() throws IOException {
		
		return getBytes(chatGroupId, groupName, unknownInt, mute);
	}
	
	public String toString() {
		
		String output = new StringBuffer()
			.append(TYPE).append(" ").append(NAME).append(" (").append(this.getClass().getName()).append(")")
			.append("\n\tChatGroupId: ").append(this.chatGroupId.getLongData())
			.append("\n\tGroupName: ").append(this.groupName.getStringData())
			.append("\n\tUnknownInt: ").append(this.unknownInt.getIntData())
			.append("\n\tMute: ").append(this.mute.getStringData())
			.toString();
		
		return output;
	}
}
