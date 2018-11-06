package com.jkbff.ao.tyrlib.chat.socket;

import com.jkbff.ao.tyrlib.packets.serialization.PacketDeserializer;
import com.jkbff.ao.tyrlib.packets.serialization.PacketSerializer;
import com.jkbff.ao.tyrlib.packets.client.BaseClientPacket;
import com.jkbff.ao.tyrlib.packets.server.BaseServerPacket;

import java.net.Socket;

/**
 * Act as AO Server
 */
public class AOServerSocket extends AOSocket<BaseClientPacket, BaseServerPacket> {
    public AOServerSocket(String id, Socket socket, PacketDeserializer<BaseClientPacket> packetDeserializer, PacketSerializer<BaseServerPacket> packetSerializer, Closeable onError) {
        super(id, socket, packetDeserializer, packetSerializer, onError);
    }
}
