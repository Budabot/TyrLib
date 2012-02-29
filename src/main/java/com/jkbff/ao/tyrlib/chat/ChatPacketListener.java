package com.jkbff.ao.tyrlib.chat;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.log4j.Logger;

import com.jkbff.ao.tyrlib.packets.BaseServerPacket;

public class ChatPacketListener extends Thread {
	
    private final DataInputStream dataInputStream;
    private final AOSocket aoBot;
    private final Logger log = Logger.getLogger(this.getClass());
    
    public ChatPacketListener(InputStream inputStream, AOSocket aoBot) {
    	this.dataInputStream = new DataInputStream(inputStream);
    	this.aoBot = aoBot;
    }

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
            } catch (IOException e) {
            	log.error("Bot Character: '" + aoBot.getCharacter() + "'", e);
            	aoBot.shutdown();
            }
        }
        
        try {
            dataInputStream.close();
        } catch (Exception e) {
        	log.error("", e);
        }
    }
}
