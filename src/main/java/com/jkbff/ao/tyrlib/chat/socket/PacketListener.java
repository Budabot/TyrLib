package com.jkbff.ao.tyrlib.chat.socket;

import com.jkbff.ao.tyrlib.packets.BasePacket;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;

public class PacketListener<T extends BasePacket> extends Thread {
    private final BlockingQueue<T> queue;
    private final DataInputStream inputStream;
    private final PacketFactory<T> packetFactory;
    private boolean stop = false;

    public PacketListener(BlockingQueue<T> queue, DataInputStream inputStream, PacketFactory<T> packetFactory) {
        this.queue = queue;
        this.inputStream = inputStream;
        this.packetFactory = packetFactory;
    }

    @Override
    public void run() {
        while (!stop) {
            try {
                queue.offer(readPacket());
            } catch (Exception e) {
                e.printStackTrace();
                close();
            }
        }
    }

    // this blocks until data is available
    public T readPacket() throws IOException {
        // read the packet bytes from the stream
        int packetId = inputStream.readUnsignedShort();
        int packetLength = inputStream.readUnsignedShort();
        byte[] payload = new byte[packetLength];
        inputStream.readFully(payload);

        T packet = packetFactory.createInstance(packetId, payload);
        if (packet == null) {
            throw new RuntimeException("Unknown packet id: '" + packetId + "'\npacketLength: '" + packetLength + "'\npayload: '" + payload + "'");
        }
        return packet;
    }

    public void close() {
        System.out.println("closing PacketListener " + getName());
        stop = true;
        try {
            inputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
