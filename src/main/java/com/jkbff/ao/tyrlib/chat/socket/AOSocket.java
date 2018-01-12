package com.jkbff.ao.tyrlib.chat.socket;

import com.jkbff.ao.tyrlib.packets.BasePacket;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class AOSocket<T extends BasePacket, U extends BasePacket> {
    private final String name;
    private final Socket socket;
    private final PacketListener<T> packetListener;
    private final PacketSender<U> packetSender;
    private final LinkedBlockingQueue<U> outboundQueue = new LinkedBlockingQueue<>();
    private final LinkedBlockingQueue<T> inboundQueue = new LinkedBlockingQueue<>();

    public AOSocket(String name, Socket socket, PacketFactory<T> packetFactory) {
        this.name = name;
        this.socket = socket;
        try {
            packetListener = new PacketListener<>(inboundQueue, new DataInputStream(socket.getInputStream()), packetFactory);
            packetSender = new PacketSender<>(outboundQueue, socket.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void start() {
        packetListener.setName(name + "PacketListener");
        packetListener.start();

        packetSender.setName(name + "PacketSender");
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
        packetListener.stopThread();
        packetSender.stopThread();
        try {
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getName() {
        return this.name;
    }
}
