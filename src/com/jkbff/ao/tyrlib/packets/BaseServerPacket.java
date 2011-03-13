package com.jkbff.ao.tyrlib.packets;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.jkbff.ao.tyrlib.packets.server.BroadcastMessage;
import com.jkbff.ao.tyrlib.packets.server.ChannelLeave;
import com.jkbff.ao.tyrlib.packets.server.ChannelMessage;
import com.jkbff.ao.tyrlib.packets.server.ChannelUpdate;
import com.jkbff.ao.tyrlib.packets.server.CharacterList;
import com.jkbff.ao.tyrlib.packets.server.CharacterReply;
import com.jkbff.ao.tyrlib.packets.server.CharacterUpdate;
import com.jkbff.ao.tyrlib.packets.server.ClientUnknownPacket;
import com.jkbff.ao.tyrlib.packets.server.FriendRemove;
import com.jkbff.ao.tyrlib.packets.server.FriendUpdate;
import com.jkbff.ao.tyrlib.packets.server.LoginError;
import com.jkbff.ao.tyrlib.packets.server.LoginOk;
import com.jkbff.ao.tyrlib.packets.server.LoginSeed;
import com.jkbff.ao.tyrlib.packets.server.Ping;
import com.jkbff.ao.tyrlib.packets.server.PrivateChannelCharacterJoin;
import com.jkbff.ao.tyrlib.packets.server.PrivateChannelCharacterLeave;
import com.jkbff.ao.tyrlib.packets.server.PrivateChannelInvite;
import com.jkbff.ao.tyrlib.packets.server.PrivateChannelKick;
import com.jkbff.ao.tyrlib.packets.server.PrivateChannelMessage;
import com.jkbff.ao.tyrlib.packets.server.PrivateGroupPartPacket;
import com.jkbff.ao.tyrlib.packets.server.PrivateGroupRefuseInvitePacket;
import com.jkbff.ao.tyrlib.packets.server.PrivateMessageReceive;
import com.jkbff.ao.tyrlib.packets.server.SimpleSystemMessage;
import com.jkbff.ao.tyrlib.packets.server.SystemMessage;
import com.jkbff.ao.tyrlib.packets.server.VicinityMessage;

public abstract class BaseServerPacket extends BasePacket {
	public static BaseServerPacket createInstance(int packetId, byte[] payload) throws IOException {
		ByteArrayInputStream byteStream = new ByteArrayInputStream(payload);
		DataInputStream dataStream = new DataInputStream(byteStream);

		switch (packetId) {
			case BroadcastMessage.TYPE:
				return new BroadcastMessage(dataStream);
			case FriendUpdate.TYPE:
				return new FriendUpdate(dataStream);
			case FriendRemove.TYPE:
				return new FriendRemove(dataStream);
			case SystemMessage.TYPE:
				return new SystemMessage(dataStream);
			case CharacterReply.TYPE:
				return new CharacterReply(dataStream);
			case CharacterUpdate.TYPE:
				return new CharacterUpdate(dataStream);
			case ClientUnknownPacket.TYPE:
				return new ClientUnknownPacket(dataStream);
			case ChannelUpdate.TYPE:
				return new ChannelUpdate(dataStream);
			case ChannelMessage.TYPE:
				return new ChannelMessage(dataStream);
			case ChannelLeave.TYPE:
				return new ChannelLeave(dataStream);
			case CharacterList.TYPE:
				return new CharacterList(dataStream);
			case LoginError.TYPE:
				return new LoginError(dataStream);
			case LoginOk.TYPE:
				return new LoginOk(dataStream);
			case LoginSeed.TYPE:
				return new LoginSeed(dataStream);
			case Ping.TYPE:
				return new Ping(dataStream);
			case PrivateChannelCharacterJoin.TYPE:
				return new PrivateChannelCharacterJoin(dataStream);
			case PrivateChannelCharacterLeave.TYPE:
				return new PrivateChannelCharacterLeave(dataStream);
			case PrivateChannelInvite.TYPE:
				return new PrivateChannelInvite(dataStream);
			case PrivateChannelKick.TYPE:
				return new PrivateChannelKick(dataStream);
			case PrivateChannelMessage.TYPE:
				return new PrivateChannelMessage(dataStream);
			case PrivateGroupPartPacket.TYPE:
				return new PrivateGroupPartPacket(dataStream);
			case PrivateGroupRefuseInvitePacket.TYPE:
				return new PrivateGroupRefuseInvitePacket(dataStream);
			case PrivateMessageReceive.TYPE:
				return new PrivateMessageReceive(dataStream);
			case SimpleSystemMessage.TYPE:
				return new SimpleSystemMessage(dataStream);
			case VicinityMessage.TYPE:
				return new VicinityMessage(dataStream);
			default:
				return null;
		}
	}
}
