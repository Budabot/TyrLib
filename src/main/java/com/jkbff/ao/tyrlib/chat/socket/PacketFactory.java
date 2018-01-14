package com.jkbff.ao.tyrlib.chat.socket;

import com.jkbff.ao.tyrlib.packets.BasePacket;

public interface PacketFactory<T extends BasePacket> {
    T createInstance(int packetId, byte[] payload);
}