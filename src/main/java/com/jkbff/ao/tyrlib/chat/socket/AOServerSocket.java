package com.jkbff.ao.tyrlib.chat.socket;

import com.jkbff.ao.tyrlib.packets.client.BaseClientPacket;
import com.jkbff.ao.tyrlib.packets.server.BaseServerPacket;

import java.net.Socket;

/**
 * Act as AO Server
 */
public class AOServerSocket extends AOSocket<BaseClientPacket, BaseServerPacket> {
    public AOServerSocket(String name, Socket socket, PacketFactory<BaseClientPacket> packetFactory) {
        super(name, socket, packetFactory);
    }
}
