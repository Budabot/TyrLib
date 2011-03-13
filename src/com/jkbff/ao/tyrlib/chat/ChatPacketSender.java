package com.jkbff.ao.tyrlib.chat;

import java.io.IOException;
import java.io.OutputStream;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.jkbff.ao.tyrlib.packets.BaseClientPacket;

public class ChatPacketSender extends Thread {

	private OutputStream outputStream;
	private BlockingQueue<BaseClientPacket> packetQueue = new ArrayBlockingQueue<BaseClientPacket>(1000);
	private AOSingleConnection aoBot;
	private Logger log = Logger.getLogger(this.getClass());

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
            	log.error(e);
            } catch (IOException e) {
            	log.error(e);
            }
        }

        try {
            outputStream.close();
        } catch (Exception e) {
        	log.error(e);
        }
	}

	public void sendPacket(BaseClientPacket packet) {
		packetQueue.add(packet);
	}

	public void setOutputStream(OutputStream outputStream) { this.outputStream = outputStream; }
	public void setAOBot(AOSingleConnection aoBot) { this.aoBot = aoBot; }
}
