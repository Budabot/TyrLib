package com.jkbff.ao.tyrlib.packets;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.jkbff.ao.tyrlib.chat.AOBot;
import com.jkbff.ao.tyrlib.packets.server.AdminMuxInfoPacket;
import com.jkbff.ao.tyrlib.packets.server.AnonymousVicinityMessagePacket;
import com.jkbff.ao.tyrlib.packets.server.BuddyAddedPacket;
import com.jkbff.ao.tyrlib.packets.server.BuddyRemovedPacket;
import com.jkbff.ao.tyrlib.packets.server.ChatMessagePacket;
import com.jkbff.ao.tyrlib.packets.server.ClientLookupPacket;
import com.jkbff.ao.tyrlib.packets.server.ClientNamePacket;
import com.jkbff.ao.tyrlib.packets.server.ClientUnknownPacket;
import com.jkbff.ao.tyrlib.packets.server.ForwardPacket;
import com.jkbff.ao.tyrlib.packets.server.GroupAnnouncePacket;
import com.jkbff.ao.tyrlib.packets.server.GroupMessagePacket;
import com.jkbff.ao.tyrlib.packets.server.GroupPartPacket;
import com.jkbff.ao.tyrlib.packets.server.LoginCharacterListPacket;
import com.jkbff.ao.tyrlib.packets.server.LoginErrorPacket;
import com.jkbff.ao.tyrlib.packets.server.LoginOkPacket;
import com.jkbff.ao.tyrlib.packets.server.LoginSeedPacket;
import com.jkbff.ao.tyrlib.packets.server.PingResponsePacket;
import com.jkbff.ao.tyrlib.packets.server.PrivateGroupClientJoinedPacket;
import com.jkbff.ao.tyrlib.packets.server.PrivateGroupClientPartPacket;
import com.jkbff.ao.tyrlib.packets.server.PrivateGroupInvitedPacket;
import com.jkbff.ao.tyrlib.packets.server.PrivateGroupKickedPacket;
import com.jkbff.ao.tyrlib.packets.server.PrivateGroupMessagePacket;
import com.jkbff.ao.tyrlib.packets.server.PrivateGroupPartPacket;
import com.jkbff.ao.tyrlib.packets.server.PrivateGroupRefuseInvitePacket;
import com.jkbff.ao.tyrlib.packets.server.PrivateMessagePacket;
import com.jkbff.ao.tyrlib.packets.server.SystemMessagePacket;
import com.jkbff.ao.tyrlib.packets.server.VicinityMessagePacket;

public abstract class BaseServerPacket extends BasePacket {
	
	private AOBot aoBot;
	
	public static BaseServerPacket createInstance(int packetId, byte[] payload) throws IOException {
		ByteArrayInputStream byteStream = new ByteArrayInputStream(payload);
		DataInputStream dataStream = new DataInputStream(byteStream);

		switch (packetId) {
			case AdminMuxInfoPacket.TYPE:
				return new AdminMuxInfoPacket(dataStream);
			case AnonymousVicinityMessagePacket.TYPE:
				return new AnonymousVicinityMessagePacket(dataStream);
			case BuddyAddedPacket.TYPE:
				return new BuddyAddedPacket(dataStream);
			case BuddyRemovedPacket.TYPE:
				return new BuddyRemovedPacket(dataStream);
			case ChatMessagePacket.TYPE:
				return new ChatMessagePacket(dataStream);
			case ClientLookupPacket.TYPE:
				return new ClientLookupPacket(dataStream);
			case ClientNamePacket.TYPE:
				return new ClientNamePacket(dataStream);
			case ClientUnknownPacket.TYPE:
				return new ClientUnknownPacket(dataStream);
			case ForwardPacket.TYPE:
				return new ForwardPacket(dataStream);
			case GroupAnnouncePacket.TYPE:
				return new GroupAnnouncePacket(dataStream);
			case GroupMessagePacket.TYPE:
				return new GroupMessagePacket(dataStream);
			case GroupPartPacket.TYPE:
				return new GroupPartPacket(dataStream);
			case LoginCharacterListPacket.TYPE:
				return new LoginCharacterListPacket(dataStream);
			case LoginErrorPacket.TYPE:
				return new LoginErrorPacket(dataStream);
			case LoginOkPacket.TYPE:
				return new LoginOkPacket(dataStream);
			case LoginSeedPacket.TYPE:
				return new LoginSeedPacket(dataStream);
			case PingResponsePacket.TYPE:
				return new PingResponsePacket(dataStream);
			case PrivateGroupClientJoinedPacket.TYPE:
				return new PrivateGroupClientJoinedPacket(dataStream);
			case PrivateGroupClientPartPacket.TYPE:
				return new PrivateGroupClientPartPacket(dataStream);
			case PrivateGroupInvitedPacket.TYPE:
				return new PrivateGroupInvitedPacket(dataStream);
			case PrivateGroupKickedPacket.TYPE:
				return new PrivateGroupKickedPacket(dataStream);
			case PrivateGroupMessagePacket.TYPE:
				return new PrivateGroupMessagePacket(dataStream);
			case PrivateGroupPartPacket.TYPE:
				return new PrivateGroupPartPacket(dataStream);
			case PrivateGroupRefuseInvitePacket.TYPE:
				return new PrivateGroupRefuseInvitePacket(dataStream);
			case PrivateMessagePacket.TYPE:
				return new PrivateMessagePacket(dataStream);
			case SystemMessagePacket.TYPE:
				return new SystemMessagePacket(dataStream);
			case VicinityMessagePacket.TYPE:
				return new VicinityMessagePacket(dataStream);
			default:
				return null;
		}
	}
	
	public AOBot getAOBot() {
		return aoBot;
	}
	public void setAOBot(AOBot aoBot) {
		this.aoBot = aoBot;
	}
}
