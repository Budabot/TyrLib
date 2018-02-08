package com.jkbff.ao.tyrlib.packets;

import com.jkbff.ao.tyrlib.packets.client.*;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

public class ClientPacketFactory implements PacketFactory<BaseClientPacket> {
    public BaseClientPacket createInstance(int packetId, byte[] payload) {
        ByteArrayInputStream byteStream = new ByteArrayInputStream(payload);
        DataInputStream dataStream = new DataInputStream(byteStream);
        switch (packetId) {
            case BuddyAdd.TYPE:
                return new BuddyAdd(dataStream);
            case BuddyRemove.TYPE:
                return new BuddyRemove(dataStream);
            case ChatCommand.TYPE:
                return new ChatCommand(dataStream);
            case CharacterLookup.TYPE:
                return new CharacterLookup(dataStream);
            case ClientModeGet.TYPE:
                return new ClientModeGet(dataStream);
            case ClientModeSet.TYPE:
                return new ClientModeSet(dataStream);
            case PublicChannelClientModeSet.TYPE:
                return new PublicChannelClientModeSet(dataStream);
            case PublicChannelDataSet.TYPE:
                return new PublicChannelDataSet(dataStream);
            case LoginRequest.TYPE:
                return new LoginRequest(dataStream);
            case LoginSelect.TYPE:
                return new LoginSelect(dataStream);
            case OnlineStatusSet.TYPE:
                return new OnlineStatusSet(dataStream);
            case PublicChannelMessage.TYPE:
                return new PublicChannelMessage(dataStream);
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
