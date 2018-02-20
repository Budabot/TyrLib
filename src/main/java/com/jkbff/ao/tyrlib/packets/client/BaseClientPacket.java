package com.jkbff.ao.tyrlib.packets.client;

import com.jkbff.ao.tyrlib.packets.BasePacket;

public abstract class BaseClientPacket extends BasePacket {
	public String getDirection() {
		return "server";
	}
}
