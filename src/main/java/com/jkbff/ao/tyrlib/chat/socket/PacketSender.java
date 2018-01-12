package com.jkbff.ao.tyrlib.chat.socket;

import com.jkbff.ao.tyrlib.packets.BasePacket;

import java.io.IOException;
import java.io.OutputStream;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class PacketSender<T extends BasePacket> extends Thread {
    private final BlockingQueue<T> queue;
    private final OutputStream outputStream;
    private boolean stop = false;

    public PacketSender(BlockingQueue<T> queue, OutputStream outputStream) {
        this.queue = queue;
        this.outputStream = outputStream;
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
                e.printStackTrace();
                stopThread();
            }
        }
    }

    public void stopThread() {
        stop = true;
        try {
            outputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
