package com.jkbff.ao.tyrlib.packets;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.jkbff.ao.tyrlib.packets.client.BuddyAddPacket;
import com.jkbff.ao.tyrlib.packets.client.BuddyRemovePacket;
import com.jkbff.ao.tyrlib.packets.client.ChatCommandPacket;
import com.jkbff.ao.tyrlib.packets.client.ClientLookupPacket;
import com.jkbff.ao.tyrlib.packets.client.ClientModeGetPacket;
import com.jkbff.ao.tyrlib.packets.client.ClientModeSetPacket;
import com.jkbff.ao.tyrlib.packets.client.GroupClientModeSetPacket;
import com.jkbff.ao.tyrlib.packets.client.GroupDataSetPacket;
import com.jkbff.ao.tyrlib.packets.client.LoginRequestPacket;
import com.jkbff.ao.tyrlib.packets.client.LoginSelectCharacterPacket;
import com.jkbff.ao.tyrlib.packets.client.OnlineStatusSetPacket;
import com.jkbff.ao.tyrlib.packets.client.OutgoingGroupMessagePacket;
import com.jkbff.ao.tyrlib.packets.client.OutgoingPrivateGroupMessagePacket;
import com.jkbff.ao.tyrlib.packets.client.OutgoingPrivateMessagePacket;
import com.jkbff.ao.tyrlib.packets.client.PingPacket;
import com.jkbff.ao.tyrlib.packets.client.PrivateGroupInviteRequestPacket;
import com.jkbff.ao.tyrlib.packets.client.PrivateGroupJoinResponsePacket;
import com.jkbff.ao.tyrlib.packets.client.PrivateGroupKickAllRequestPacket;
import com.jkbff.ao.tyrlib.packets.client.PrivateGroupKickRequestPacket;
import com.jkbff.ao.tyrlib.packets.client.PrivateGroupPartPacket;


public abstract class BaseClientPacket extends BasePacket {
	public static BaseClientPacket createInstance(int packetId, byte[] payload) throws IOException {
		ByteArrayInputStream byteStream = new ByteArrayInputStream(payload);
		DataInputStream dataStream = new DataInputStream(byteStream);

		switch (packetId) {
			case BuddyAddPacket.TYPE:
				return new BuddyAddPacket(dataStream);
			case BuddyRemovePacket.TYPE:
				return new BuddyRemovePacket(dataStream);
			case ChatCommandPacket.TYPE:
				return new ChatCommandPacket(dataStream);
			case ClientLookupPacket.TYPE:
				return new ClientLookupPacket(dataStream);
			case ClientModeGetPacket.TYPE:
				return new ClientModeGetPacket(dataStream);
			case ClientModeSetPacket.TYPE:
				return new ClientModeSetPacket(dataStream);
			case GroupClientModeSetPacket.TYPE:
				return new GroupClientModeSetPacket(dataStream);
			case GroupDataSetPacket.TYPE:
				return new GroupDataSetPacket(dataStream);
			case LoginRequestPacket.TYPE:
				return new LoginRequestPacket(dataStream);
			case LoginSelectCharacterPacket.TYPE:
				return new LoginSelectCharacterPacket(dataStream);
			case OnlineStatusSetPacket.TYPE:
				return new OnlineStatusSetPacket(dataStream);
			case OutgoingGroupMessagePacket.TYPE:
				return new OutgoingGroupMessagePacket(dataStream);
			case OutgoingPrivateGroupMessagePacket.TYPE:
				return new OutgoingPrivateGroupMessagePacket(dataStream);
			case OutgoingPrivateMessagePacket.TYPE:
				return new OutgoingPrivateMessagePacket(dataStream);
			case PingPacket.TYPE:
				return new PingPacket(dataStream);
			case PrivateGroupInviteRequestPacket.TYPE:
				return new PrivateGroupInviteRequestPacket(dataStream);
			case PrivateGroupJoinResponsePacket.TYPE:
				return new PrivateGroupJoinResponsePacket(dataStream);
			case PrivateGroupKickAllRequestPacket.TYPE:
				return new PrivateGroupKickAllRequestPacket(dataStream);
			case PrivateGroupKickRequestPacket.TYPE:
				return new PrivateGroupKickRequestPacket(dataStream);
			case PrivateGroupPartPacket.TYPE:
				return new PrivateGroupPartPacket(dataStream);
			default:
				return null;
		}
	}
}
