package com.jkbff.ao.tyrlib.packets.serialization;

import com.jkbff.ao.tyrlib.packets.client.BaseClientPacket;

import java.io.IOException;

public class ClientPacketSerializer implements PacketSerializer<BaseClientPacket> {
    @Override
    public byte[] toBytes(BaseClientPacket packet) {
        try {
            return packet.getBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
