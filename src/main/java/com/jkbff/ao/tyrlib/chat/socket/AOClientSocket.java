package com.jkbff.ao.tyrlib.chat.socket;

import com.jkbff.ao.tyrlib.packets.PacketFactory;
import com.jkbff.ao.tyrlib.packets.client.BaseClientPacket;
import com.jkbff.ao.tyrlib.packets.server.BaseServerPacket;

import java.net.Socket;

/**
 * Act as AO Client
 */
public class AOClientSocket extends AOSocket<BaseServerPacket, BaseClientPacket> {
    public AOClientSocket(String id, Socket socket, PacketFactory<BaseServerPacket> packetFactory, Closeable onError) {
        super(id, socket, packetFactory, onError);
    }
}
