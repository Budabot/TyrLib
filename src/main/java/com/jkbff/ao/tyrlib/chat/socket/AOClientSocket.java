package com.jkbff.ao.tyrlib.chat.socket;

import com.jkbff.ao.tyrlib.packets.client.BaseClientPacket;
import com.jkbff.ao.tyrlib.packets.server.BaseServerPacket;

import java.net.Socket;

public class AOClientSocket extends AOSocket<BaseServerPacket, BaseClientPacket> {
    public AOClientSocket(Socket socket, PacketFactory<BaseServerPacket> packetFactory) {
        super(socket, packetFactory);
    }
}
