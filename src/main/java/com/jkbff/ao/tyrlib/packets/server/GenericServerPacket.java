package com.jkbff.ao.tyrlib.packets.server;

import java.io.DataInputStream;
import java.io.IOException;

public class GenericServerPacket extends BaseServerPacket {
    protected final int packetType;
    protected final byte[] data;

    public GenericServerPacket(int packetType, int size, DataInputStream input) {
        this.packetType = packetType;
        this.data = new byte[size];
        try {
            input.readFully(this.data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public GenericServerPacket(int packetType, byte[] data) {
        this.packetType = packetType;
        this.data = data;
    }

    @Override
    public int getPacketType() {
        return this.packetType;
    }

    @Override
    public byte[] getBytes() throws IOException {
        return data;
    }
}
