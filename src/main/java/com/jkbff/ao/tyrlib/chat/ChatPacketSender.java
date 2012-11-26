package com.jkbff.ao.tyrlib.chat;

import java.io.IOException;
import java.io.OutputStream;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.log4j.Logger;

import com.jkbff.ao.tyrlib.packets.BaseClientPacket;
import com.jkbff.ao.tyrlib.packets.client.PrivateMessageSend;
import com.jkbff.ao.tyrlib.packets.server.ChannelMessage;

public class ChatPacketSender extends Thread {

	private final OutputStream outputStream;
	private final BlockingQueue<BaseClientPacket> packetQueue = new LinkedBlockingQueue<BaseClientPacket>();
	private final BlockingQueue<BaseClientPacket> throttledPacketQueue = new LinkedBlockingQueue<BaseClientPacket>();
	private final AOSocket aoBot;
	private final Logger log = Logger.getLogger(this.getClass());
	private long queueTime = 0;

	private long timeBetweenPackets = 2000;
	private long numBurstPackets = 3;

	public ChatPacketSender(OutputStream outputStream, AOSocket aoBot) {
		this.outputStream = outputStream;
		this.aoBot = aoBot;
	}

	@Override
	public void run() {
		while (!aoBot.shouldStop) {
			try {
				BaseClientPacket packet = getNextPacket();
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

	private synchronized BaseClientPacket getNextPacket() throws InterruptedException {
		BaseClientPacket packet = null;
		while (packet == null) {
			long currentTime = System.currentTimeMillis();
			if (throttledPacketQueue.peek() != null && queueTime <= currentTime) {
				queueTime = Math.max(currentTime - (timeBetweenPackets * numBurstPackets), queueTime);
				queueTime += timeBetweenPackets;
				packet = throttledPacketQueue.poll();
			} else {
				packet = packetQueue.poll();
			}
			if (packet == null) {
				if (aoBot.shouldStop) {
					break;
				}
				long newTime = queueTime - currentTime;
				if (newTime > 0) {
					wait(newTime);
				} else {
					wait();
				}
			}
		}
		return packet;
	}

	public synchronized void sendPacket(BaseClientPacket packet) {
		switch (packet.getPacketType()) {
			case ChannelMessage.TYPE:
			case PrivateMessageSend.TYPE:
				throttledPacketQueue.add(packet);
				break;
			default:
				packetQueue.add(packet);
				break;
		}
		notify();
	}

	public long getTimeBetweenPackets() {
		return timeBetweenPackets;
	}

	public void setTimeBetweenPackets(long timeBetweenPackets) {
		this.timeBetweenPackets = timeBetweenPackets;
	}

	public long getNumBurstPackets() {
		return numBurstPackets;
	}

	public void setNumBurstPackets(long numBurstPackets) {
		this.numBurstPackets = numBurstPackets;
	}
}
