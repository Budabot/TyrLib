package com.jkbff.ao.tyrlib.packets;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.jkbff.ao.tyrlib.packets.client.FriendUpdate;
import com.jkbff.ao.tyrlib.packets.client.FriendRemove;
import com.jkbff.ao.tyrlib.packets.client.ChatCommandPacket;
import com.jkbff.ao.tyrlib.packets.client.CharacterRequest;
import com.jkbff.ao.tyrlib.packets.client.ClientModeGetPacket;
import com.jkbff.ao.tyrlib.packets.client.ClientModeSetPacket;
import com.jkbff.ao.tyrlib.packets.client.GroupClientModeSetPacket;
import com.jkbff.ao.tyrlib.packets.client.GroupDataSetPacket;
import com.jkbff.ao.tyrlib.packets.client.LoginRequest;
import com.jkbff.ao.tyrlib.packets.client.LoginSelect;
import com.jkbff.ao.tyrlib.packets.client.OnlineStatusSetPacket;
import com.jkbff.ao.tyrlib.packets.client.ChannelMessage;
import com.jkbff.ao.tyrlib.packets.client.PrivateChannelMessage;
import com.jkbff.ao.tyrlib.packets.client.PrivateMessageSend;
import com.jkbff.ao.tyrlib.packets.client.Ping;
import com.jkbff.ao.tyrlib.packets.client.PrivateChannelInvite;
import com.jkbff.ao.tyrlib.packets.client.PrivateChannelAccept;
import com.jkbff.ao.tyrlib.packets.client.PrivateChannelKickAll;
import com.jkbff.ao.tyrlib.packets.client.PrivateChannelKick;
import com.jkbff.ao.tyrlib.packets.client.PrivateChannelLeave;


public abstract class BaseClientPacket extends BasePacket {
	public static BaseClientPacket createInstance(int packetId, byte[] payload) throws IOException {
		ByteArrayInputStream byteStream = new ByteArrayInputStream(payload);
		DataInputStream dataStream = new DataInputStream(byteStream);

		switch (packetId) {
			case FriendUpdate.TYPE:
				return new FriendUpdate(dataStream);
			case FriendRemove.TYPE:
				return new FriendRemove(dataStream);
			case ChatCommandPacket.TYPE:
				return new ChatCommandPacket(dataStream);
			case CharacterRequest.TYPE:
				return new CharacterRequest(dataStream);
			case ClientModeGetPacket.TYPE:
				return new ClientModeGetPacket(dataStream);
			case ClientModeSetPacket.TYPE:
				return new ClientModeSetPacket(dataStream);
			case GroupClientModeSetPacket.TYPE:
				return new GroupClientModeSetPacket(dataStream);
			case GroupDataSetPacket.TYPE:
				return new GroupDataSetPacket(dataStream);
			case LoginRequest.TYPE:
				return new LoginRequest(dataStream);
			case LoginSelect.TYPE:
				return new LoginSelect(dataStream);
			case OnlineStatusSetPacket.TYPE:
				return new OnlineStatusSetPacket(dataStream);
			case ChannelMessage.TYPE:
				return new ChannelMessage(dataStream);
			case PrivateChannelMessage.TYPE:
				return new PrivateChannelMessage(dataStream);
			case PrivateMessageSend.TYPE:
				return new PrivateMessageSend(dataStream);
			case Ping.TYPE:
				return new Ping(dataStream);
			case PrivateChannelInvite.TYPE:
				return new PrivateChannelInvite(dataStream);
			case PrivateChannelAccept.TYPE:
				return new PrivateChannelAccept(dataStream);
			case PrivateChannelKickAll.TYPE:
				return new PrivateChannelKickAll(dataStream);
			case PrivateChannelKick.TYPE:
				return new PrivateChannelKick(dataStream);
			case PrivateChannelLeave.TYPE:
				return new PrivateChannelLeave(dataStream);
			default:
				return null;
		}
	}
}
