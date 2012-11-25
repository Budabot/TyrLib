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

	public static final long TIME_BETWEEN_PACKETS = 2000;
	public static final long BURST_PACKETS = 3;

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
				} else {
					long newTime = System.currentTimeMillis() - queueTime;
					if (newTime > 0) {
						synchronized (this) {
							this.wait(newTime);
						}
					}
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

	private BaseClientPacket getNextPacket() throws InterruptedException {
		if (throttledPacketQueue.peek() != null && queueTime < System.currentTimeMillis()) {
			queueTime = Math.max(System.currentTimeMillis() - TIME_BETWEEN_PACKETS * BURST_PACKETS, queueTime);
			queueTime += TIME_BETWEEN_PACKETS;
			return throttledPacketQueue.poll();
		} else {
			return packetQueue.poll();
		}
	}

	public void sendPacket(BaseClientPacket packet) {
		switch (packet.getPacketType()) {
			case ChannelMessage.TYPE:
			case PrivateMessageSend.TYPE:
				throttledPacketQueue.add(packet);
				break;
			default:
				packetQueue.add(packet);
				break;
		}
		synchronized (this) {
			notify();
		}
	}
}
