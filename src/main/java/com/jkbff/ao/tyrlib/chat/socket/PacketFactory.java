package com.jkbff.ao.tyrlib.chat.socket;

interface PacketFactory<T> {
    T createInstance(int packetId, byte[] payload);
}
