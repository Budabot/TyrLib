package com.jkbff.ao.tyrlib.chat.socket;

import com.jkbff.ao.tyrlib.packets.BasePacket;
import com.jkbff.ao.tyrlib.packets.PacketFactory;
import org.apache.log4j.Logger;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class AOSocket<T extends BasePacket, U extends BasePacket> implements Closeable {
    private final String id;
    private final Socket socket;
    private final PacketListener<T> packetListener;
    private final PacketSender<U> packetSender;
    private final LinkedBlockingQueue<U> outboundQueue = new LinkedBlockingQueue<>();
    private final LinkedBlockingQueue<T> inboundQueue = new LinkedBlockingQueue<>();
    private final Logger logger = Logger.getLogger(getClass());

    public AOSocket(String id, Socket socket, PacketFactory<T> packetFactory, Closeable onError) {
        this.id = id;
        this.socket = socket;
        try {
            packetListener = new PacketListener<>(inboundQueue, new DataInputStream(socket.getInputStream()), packetFactory, onError);
            packetListener.setName(id + "PacketListener");

            packetSender = new PacketSender<>(outboundQueue, socket.getOutputStream(), onError);
            packetSender.setName(id + "PacketSender");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void start() {
        packetListener.start();
        packetSender.start();
    }

    public boolean isAlive() {
        return packetListener.isAlive() && packetSender.isAlive();
    }

    public void sendPacket(U packet) {
        outboundQueue.offer(packet);
    }

    public T readPacket() {
        try {
            return inboundQueue.poll(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void close() {
        logger.warn("closing AOSocket " + id);
        packetListener.close();
        packetSender.close();
        try {
            socket.close();
        } catch (IOException e) {
            logger.error("", e);
        }
    }
}
