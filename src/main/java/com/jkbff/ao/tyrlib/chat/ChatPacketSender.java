package com.jkbff.ao.tyrlib.chat;

import java.io.IOException;
import java.io.OutputStream;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.jkbff.ao.tyrlib.packets.BaseClientPacket;

public class ChatPacketSender extends Thread {

	private final OutputStream outputStream;
	private final BlockingQueue<BaseClientPacket> packetQueue = new LinkedBlockingQueue<BaseClientPacket>();
	private final AOSocket aoBot;
	private final Logger log = Logger.getLogger(this.getClass());
	
	public ChatPacketSender(OutputStream outputStream, AOSocket aoBot) {
		this.outputStream = outputStream;
		this.aoBot = aoBot;
	}

	@Override
	public void run() {
        while (!aoBot.shouldStop) {
            try {
                BaseClientPacket packet = packetQueue.poll(1, TimeUnit.SECONDS);
                if (packet != null) {
                	byte[] bytes = packet.getBytes();
                	outputStream.write(bytes);
                }
            } catch (InterruptedException e) {
            	log.error("", e);
            } catch (IOException e) {
            	log.error("", e);
            	aoBot.shutdown();
            }
        }

        try {
            outputStream.close();
        } catch (Exception e) {
        	log.error("", e);
        }
	}

	public void sendPacket(BaseClientPacket packet) {
		packetQueue.add(packet);
	}
}
