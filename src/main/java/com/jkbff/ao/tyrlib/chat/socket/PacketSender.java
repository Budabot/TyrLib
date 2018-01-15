package com.jkbff.ao.tyrlib.chat.socket;

import com.jkbff.ao.tyrlib.packets.BasePacket;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.OutputStream;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class PacketSender<T extends BasePacket> extends Thread implements Closeable {
    private final BlockingQueue<T> queue;
    private final OutputStream outputStream;
    private final Closeable onError;
    private boolean stop = false;
    private final Logger logger = Logger.getLogger(getClass());

    public PacketSender(BlockingQueue<T> queue, OutputStream outputStream, Closeable onError) {
        this.queue = queue;
        this.outputStream = outputStream;
        this.onError = onError;
    }

    @Override
    public void run() {
        while (!stop) {
            try {
                T packet = queue.poll(1, TimeUnit.SECONDS);
                if (packet != null) {
                    outputStream.write(packet.getBytes());
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
