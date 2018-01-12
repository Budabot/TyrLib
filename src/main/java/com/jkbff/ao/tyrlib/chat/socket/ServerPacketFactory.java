package com.jkbff.ao.tyrlib.chat.socket;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

import com.jkbff.ao.tyrlib.packets.server.*;

public class ServerPacketFactory implements PacketFactory<BaseServerPacket> {
    public BaseServerPacket createInstance(int packetId, byte[] payload) {
        ByteArrayInputStream byteStream = new ByteArrayInputStream(payload);
        DataInputStream dataStream = new DataInputStream(byteStream);
        switch(packetId) {
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
            case Pong.TYPE:
                return new Pong(dataStream);
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
