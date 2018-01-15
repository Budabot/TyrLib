package com.jkbff.ao.tyrlib.chat.socket;

import com.jkbff.ao.tyrlib.packets.client.BaseClientPacket;
import com.jkbff.ao.tyrlib.packets.server.BaseServerPacket;

import java.net.Socket;

/**
 * Act as AO Server
 */
public class AOServerSocket extends AOSocket<BaseClientPacket, BaseServerPacket> {
    public AOServerSocket(String id, Socket socket, PacketFactory<BaseClientPacket> packetFactory, Closeable onError) {
        super(id, socket, packetFactory, onError);
    }
}
