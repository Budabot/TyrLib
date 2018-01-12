package com.jkbff.ao.tyrlib.chat.socket;

import com.jkbff.ao.tyrlib.packets.client.BaseClientPacket;
import com.jkbff.ao.tyrlib.packets.server.BaseServerPacket;

import java.net.Socket;

public class AOServerSocket extends AOSocket<BaseClientPacket, BaseServerPacket> {
    public AOServerSocket(Socket socket, PacketFactory<BaseClientPacket> packetFactory) {
        super(socket, packetFactory);
    }
}
