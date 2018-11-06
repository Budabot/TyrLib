package com.jkbff.ao.tyrlib.packets.serialization;

import java.io.IOException;

public interface PacketSerializer<T> {
    byte[] toBytes(T packet) throws IOException;
}
