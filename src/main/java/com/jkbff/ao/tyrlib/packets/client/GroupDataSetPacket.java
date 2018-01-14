package com.jkbff.ao.tyrlib.packets.client;

import java.io.DataInputStream;
import java.io.IOException;

import sk.sigp.aobot.client.types.ChannelId;
import sk.sigp.aobot.client.types.Int;
import sk.sigp.aobot.client.types.Text;

/**
 * @description Deprecated. At one time, used to stop the server from sending channels to the client.
 * 	Essentially, muting channels on the server-side. see: http://aodevs.com/index.php/topic,181.0.html
 * @author Jason
 *
 */
public class GroupDataSetPacket extends BaseClientPacket {

	public static final int TYPE = 64;

	protected final ChannelId channelId;
	protected final Int flags;
	protected final Text mute;
	
	public GroupDataSetPacket(DataInputStream input) {
		channelId = new ChannelId(input);
		flags = new Int(input);
		mute = new Text(input);
	}
	
	public GroupDataSetPacket(int channelId, int flags, String mute) {
		this.channelId = new ChannelId(channelId);
		this.flags = new Int(flags);
		this.mute = new Text(mute);
	}
	
	public long getChannelId() {
		return channelId.getData();
	}
	
	public int getFlags() {
		return flags.getData();
	}
	
	public String getMute() {
		return mute.getData();
	}
	
	public byte[] getBytes() throws IOException {
		return getBytes(channelId, flags, mute);
	}
	
	public int getPacketType() {
		return GroupDataSetPacket.TYPE;
	}
	
	public String toString() {
		String output = new StringBuffer()
			.append(TYPE).append(" ").append(this.getClass().getSimpleName())
			.append("\n\tChannelId: ").append(channelId)
			.append("\n\tFlags: ").append(flags)
			.append("\n\tMute: ").append(mute)
			.toString();
	
		return output;
	}
}
