package com.jkbff.ao.tyrlib.packets.serialization;

import com.jkbff.ao.tyrlib.packets.server.BaseServerPacket;

import java.io.IOException;

public class ServerPacketSerializer implements PacketSerializer<BaseServerPacket> {
    @Override
    public byte[] toBytes(BaseServerPacket packet) {
        try {
            return packet.getBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
