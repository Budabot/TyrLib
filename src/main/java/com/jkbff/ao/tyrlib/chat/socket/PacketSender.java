package com.jkbff.ao.tyrlib.chat.socket;

import com.jkbff.ao.tyrlib.packets.serialization.PacketSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.OutputStream;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class PacketSender<T> extends Thread implements Closeable {
    private final BlockingQueue<T> queue;
    private final OutputStream outputStream;
    private final PacketSerializer<T> packetSerializer;
    private final Closeable onError;
    private boolean stop = false;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public PacketSender(BlockingQueue<T> queue, OutputStream outputStream, PacketSerializer<T> packetSerializer, Closeable onError) {
        this.queue = queue;
        this.outputStream = outputStream;
        this.packetSerializer = packetSerializer;
        this.onError = onError;
    }

    @Override
    public void run() {
        while (!stop) {
            try {
                T packet = queue.poll(1, TimeUnit.SECONDS);
                if (packet != null) {
                    outputStream.write(packetSerializer.toBytes(packet));
                }
            } catch (Exception e) {
                logger.error("", e);
                onError.close();
            }
        }
    }

    public void close() {
        stop = true;
        logger.warn("closing PacketSender " + getName());
        try {
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
