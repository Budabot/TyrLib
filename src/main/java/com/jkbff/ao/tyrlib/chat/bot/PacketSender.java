package com.jkbff.ao.tyrlib.chat.bot;

import com.jkbff.ao.tyrlib.chat.socket.AOSocket;
import com.jkbff.ao.tyrlib.packets.client.BaseClientPacket;
import com.jkbff.ao.tyrlib.packets.server.BaseServerPacket;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class PacketSender extends Thread {
    private final AOSocket<BaseServerPacket, BaseClientPacket> aoSocket;
    private final BlockingQueue<BaseClientPacket> queue;
    private boolean stop = false;

    public PacketSender(AOSocket<BaseServerPacket, BaseClientPacket> aoSocket, BlockingQueue<BaseClientPacket> queue) {
        this.aoSocket = aoSocket;
        this.queue = queue;
    }

    @Override
    public void run() {
        while (!stop) {
            try {
                BaseClientPacket packet = queue.poll(1, TimeUnit.SECONDS);
                if (packet != null) {
                    aoSocket.sendPacket(packet);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                stopThread();
            }
        }
    }

    public void stopThread() {
        stop = true;
    }
}
