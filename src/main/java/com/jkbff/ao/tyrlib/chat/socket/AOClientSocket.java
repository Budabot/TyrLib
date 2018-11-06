package com.jkbff.ao.tyrlib.chat.socket;

import com.jkbff.ao.tyrlib.packets.serialization.PacketDeserializer;
import com.jkbff.ao.tyrlib.packets.serialization.PacketSerializer;
import com.jkbff.ao.tyrlib.packets.client.BaseClientPacket;
import com.jkbff.ao.tyrlib.packets.server.BaseServerPacket;

import java.net.Socket;

/**
 * Act as AO Client
 */
public class AOClientSocket extends AOSocket<BaseServerPacket, BaseClientPacket> {
    public AOClientSocket(String id, Socket socket, PacketDeserializer<BaseServerPacket> packetDeserializer, PacketSerializer<BaseClientPacket> packetSerializer, Closeable onError) {
        super(id, socket, packetDeserializer, packetSerializer, onError);
    }
}
