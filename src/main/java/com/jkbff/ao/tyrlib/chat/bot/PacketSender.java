package com.jkbff.ao.tyrlib.chat.bot;

import com.jkbff.ao.tyrlib.chat.socket.AOSocket;
import com.jkbff.ao.tyrlib.packets.client.BaseClientPacket;
import com.jkbff.ao.tyrlib.packets.server.BaseServerPacket;

import java.util.concurrent.BlockingQueue;

public class PacketSender extends Thread {
    private final AOSocket<BaseServerPacket, BaseClientPacket> aoSocket;
    private final BlockingQueue<BaseClientPacket> queue;

    public PacketSender(AOSocket<BaseServerPacket, BaseClientPacket> aoSocket, BlockingQueue<BaseClientPacket> queue) {
        this.aoSocket = aoSocket;
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                aoSocket.sendPacket(queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
