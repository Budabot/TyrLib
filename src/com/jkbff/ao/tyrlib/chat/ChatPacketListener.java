package com.jkbff.ao.tyrlib.chat;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.jkbff.ao.tyrlib.packets.BaseServerPacket;

public class ChatPacketListener extends Thread {
	
    private DataInputStream dataInputStream;
    private AOBot aoBot;

    @Override
    public void run() {
        while (!aoBot.shouldStop) {

            int packetId = -1;
            int packetLength = -1;
            byte[] payload = null;
            try {
                packetId = dataInputStream.readUnsignedShort();
                packetLength = dataInputStream.readUnsignedShort();
                payload = new byte[packetLength];
                dataInputStream.readFully(payload);

                BaseServerPacket packet = BaseServerPacket.createInstance(packetId, payload);
                if (packet == null) {
                	System.err.println("Unknow packet!! packet id: '" + packetId + "'\npacketLength: '" + packetLength + "'\npayload: '" + payload + "'");
                } else {
                	aoBot.processIncomingPacket(packet);
                }
            } catch (IOException e) {
                e.printStackTrace();
                break;
            } catch (Exception e) {
                System.err.println("packet id: '" + packetId + "'\npacketLength: '" + packetLength + "'\npayload: '" + payload + "'");
                e.printStackTrace();
            }
        }

        try {
        	dataInputStream.close();
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {
        	dataInputStream = null;
        }
    }

    public void setInputStream(InputStream inputStream) { dataInputStream = new DataInputStream(inputStream); }
    public void setAOBot(AOBot aoBot) { this.aoBot = aoBot; }
}
