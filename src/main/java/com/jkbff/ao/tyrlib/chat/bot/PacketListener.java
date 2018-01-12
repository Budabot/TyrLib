package com.jkbff.ao.tyrlib.chat.bot;

import com.jkbff.ao.tyrlib.chat.socket.AOSocket;
import com.jkbff.ao.tyrlib.packets.client.BaseClientPacket;
import com.jkbff.ao.tyrlib.packets.server.BaseServerPacket;

import java.io.IOException;
import java.util.concurrent.BlockingQueue;

public class PacketListener extends Thread {
    private final AOSocket<BaseServerPacket, BaseClientPacket> aoSocket;
    private final BlockingQueue<BaseServerPacket> queue;

    public PacketListener(AOSocket<BaseServerPacket, BaseClientPacket> aoSocket, BlockingQueue<BaseServerPacket> queue) {
        this.aoSocket = aoSocket;
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                queue.offer(aoSocket.readPacket());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
