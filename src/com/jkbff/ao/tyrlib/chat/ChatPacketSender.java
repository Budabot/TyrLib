package com.jkbff.ao.tyrlib.chat;

import java.io.IOException;
import java.io.OutputStream;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import com.jkbff.ao.tyrlib.packets.BaseClientPacket;

public class ChatPacketSender extends Thread {

	private OutputStream outputStream;
	private BlockingQueue<BaseClientPacket> packetQueue = new ArrayBlockingQueue<BaseClientPacket>(1000);
	private AOBot aoBot;

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

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	public void sendPacket(BaseClientPacket packet) {
		packetQueue.add(packet);
	}

	public void setOutputStream(OutputStream outputStream) { this.outputStream = outputStream; }
	public void setAOBot(AOBot aoBot) { this.aoBot = aoBot; }
}
