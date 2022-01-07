package com.jkbff.ao.tyrlib.chat.socket;

import com.jkbff.ao.tyrlib.packets.serialization.PacketDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.BlockingQueue;

public class PacketListener<T> extends Thread implements Closeable {
    private final BlockingQueue<T> queue;
    private final DataInputStream inputStream;
    private final PacketDeserializer<T> packetDeserializer;
    private final Closeable onError;
    private boolean stop = false;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public PacketListener(BlockingQueue<T> queue, DataInputStream inputStream, PacketDeserializer<T> packetDeserializer, Closeable onError) {
        this.queue = queue;
        this.inputStream = inputStream;
        this.packetDeserializer = packetDeserializer;
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

        T packet = packetDeserializer.toInstance(packetId, payload);
        if (packet == null) {
            throw new RuntimeException("Unknown packet id: '" + packetId + "'\npacketLength: '" + packetLength + "'\npayload: '" + Arrays.toString(payload) + "'");
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
