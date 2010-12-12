package com.jkbff.ao.tyrlib.packets.client;

import java.io.DataInputStream;
import java.io.IOException;

import sk.sigp.aobot.client.types.ChatGroupId;
import sk.sigp.aobot.client.types.Int;
import sk.sigp.aobot.client.types.Raw;

import com.jkbff.ao.tyrlib.packets.BaseClientPacket;

/**
 * @description Deprecated. At one time, used to stop the server from sending channels to the client.
 * 	Essentially, muting channels on the server-side. see: http://aodevs.com/index.php/topic,181.0.html
 * @author Jason
 *
 */
public class GroupDataSetPacket extends BaseClientPacket {

	public static final int TYPE = 64;
	public static final String NAME = "AOCP_GROUP_DATA_SET";
	
	private ChatGroupId chatGroupId;
	private Int flags;
	private Raw mute;
	
	public GroupDataSetPacket(DataInputStream input) throws IOException {
		chatGroupId = new ChatGroupId(input);
		flags = new Int(input);
		mute = new Raw(input);
	}
	
	public GroupDataSetPacket(int chatGroupId, int flags, String mute) {
		this.chatGroupId = new ChatGroupId(chatGroupId);
		this.flags = new Int(flags);
		this.mute = new Raw(mute);
	}
	
	public byte[] getBytes() throws IOException {
		return getBytes(chatGroupId, flags, mute);
	}
	
	public int getPacketType() {
		return GroupDataSetPacket.TYPE;
	}
	
	public String toString() {
		String output = new StringBuffer()
			.append(TYPE).append(" ").append(NAME).append(" (").append(this.getClass().getName()).append(")")
			.append("\n\tChatGroupId: ").append(chatGroupId)
			.append("\n\tFlags: ").append(flags)
			.append("\n\tMute: ").append(mute)
			.toString();
	
		return output;
	}
}
