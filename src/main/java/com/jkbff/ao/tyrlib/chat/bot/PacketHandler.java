package com.jkbff.ao.tyrlib.chat.bot;

import com.jkbff.ao.tyrlib.packets.server.BaseServerPacket;

public interface PacketHandler {
    void processPacket(BaseServerPacket packet);
}
