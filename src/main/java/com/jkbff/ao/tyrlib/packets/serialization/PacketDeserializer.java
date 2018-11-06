package com.jkbff.ao.tyrlib.packets.serialization;

public interface PacketDeserializer<T> {
    T toInstance(int packetId, byte[] payload);
}
