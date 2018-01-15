package com.jkbff.ao.tyrlib.chat.socket;

import com.jkbff.ao.tyrlib.packets.BasePacket;
import com.jkbff.ao.tyrlib.packets.PacketFactory;
import org.apache.log4j.Logger;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;

public class PacketListener<T extends BasePacket> extends Thread implements Closeable {
    private final BlockingQueue<T> queue;
    private final DataInputStream inputStream;
    private final PacketFactory<T> packetFactory;
    private final Closeable onError;
    private boolean stop = false;
    private final Logger logger = Logger.getLogger(getClass());

    public PacketListener(BlockingQueue<T> queue, DataInputStream inputStream, PacketFactory<T> packetFactory, Closeable onError) {
        this.queue = queue;
        this.inputStream = inputStream;
        this.packetFactory = packetFactory;
        this.onError = onError;
    }

    @Override
    public void run() {
        while (!stop) {
            try {
                queue.offer(readPacket());
            } catch (Exception e) {
                logger.error("", e);
                onError.close();
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
        stop = true;
        logger.warn("closing PacketListener " + getName());
        try {
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
