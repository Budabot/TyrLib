package com.jkbff.ao.tyrlib.chat;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.log4j.Logger;

import com.jkbff.ao.tyrlib.packets.BaseServerPacket;

public class ChatPacketListener extends Thread {
	
    private DataInputStream dataInputStream;
    private AOConnection aoBot;
    private Logger log = Logger.getLogger(this.getClass());

    @Override
    public void run() {
        while (!aoBot.shouldStop) {
            int packetId = -1;
            int packetLength = -1;
            byte[] payload = null;

            // read the packet bytes from the stream
            try {
                packetId = dataInputStream.readUnsignedShort();
                packetLength = dataInputStream.readUnsignedShort();
                payload = new byte[packetLength];
                dataInputStream.readFully(payload);
            } catch (IOException e) {
            	if (!aoBot.shouldStop) {
            		log.error("Bot Character: '" + aoBot.getCharacter() + "'", e);
            	}
            }
            
            // create a packet from the bytes read in and send to the bot to process
            try {
	            BaseServerPacket packet = BaseServerPacket.createInstance(packetId, payload);
	            if (packet == null) {
	            	log.error("Unknown packet!! packet id: '" + packetId + "'\npacketLength: '" + packetLength + "'\npayload: '" + payload + "'");
	            } else {
	            	aoBot.processIncomingPacket(packet);
	            }
            } catch (Exception e) {
                log.error("Bot Character: '" + aoBot.getCharacter() + "' packet id: '" + packetId + "'", e);
            }
        }
    }

    public void setInputStream(InputStream inputStream) { dataInputStream = new DataInputStream(inputStream); }
    public void setAOBot(AOConnection aoBot) { this.aoBot = aoBot; }
}
