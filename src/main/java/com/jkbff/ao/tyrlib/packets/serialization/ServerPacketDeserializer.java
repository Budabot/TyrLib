package com.jkbff.ao.tyrlib.packets.serialization;

import com.jkbff.ao.tyrlib.packets.server.*;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

public class ServerPacketDeserializer implements PacketDeserializer<BaseServerPacket> {
    @Override
    public BaseServerPacket toInstance(int packetId, byte[] payload) {
        ByteArrayInputStream byteStream = new ByteArrayInputStream(payload);
        DataInputStream dataStream = new DataInputStream(byteStream);
        switch(packetId) {
            case BroadcastMessage.TYPE:
                return new BroadcastMessage(dataStream);
            case BuddyAdded.TYPE:
                return new BuddyAdded(dataStream);
            case BuddyRemoved.TYPE:
                return new BuddyRemoved(dataStream);
            case SystemMessage.TYPE:
                return new SystemMessage(dataStream);
            case CharacterReply.TYPE:
                return new CharacterReply(dataStream);
            case CharacterUpdate.TYPE:
                return new CharacterUpdate(dataStream);
            case CharacterUnknown.TYPE:
                return new CharacterUnknown(dataStream);
            case PublicChannelJoined.TYPE:
                return new PublicChannelJoined(dataStream);
            case com.jkbff.ao.tyrlib.packets.server.PublicChannelMessage.TYPE:
                return new PublicChannelMessage(dataStream);
            case PublicChannelLeft.TYPE:
                return new PublicChannelLeft(dataStream);
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
            case PrivateChannelCharacterJoined.TYPE:
                return new PrivateChannelCharacterJoined(dataStream);
            case PrivateChannelCharacterLeft.TYPE:
                return new PrivateChannelCharacterLeft(dataStream);
            case PrivateChannelInvited.TYPE:
                return new PrivateChannelInvited(dataStream);
            case PrivateChannelKicked.TYPE:
                return new PrivateChannelKicked(dataStream);
            case com.jkbff.ao.tyrlib.packets.server.PrivateChannelMessage.TYPE:
                return new PrivateChannelMessage(dataStream);
            case PrivateChannelLeft.TYPE:
                return new PrivateChannelLeft(dataStream);
            case PrivateChannelInviteRefused.TYPE:
                return new PrivateChannelInviteRefused(dataStream);
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
