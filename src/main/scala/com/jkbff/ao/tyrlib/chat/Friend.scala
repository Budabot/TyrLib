package com.jkbff.ao.tyrlib.chat

class Friend(val charId: Long, val online: OnlineStatus.Value, val status: String) {
	
}

object OnlineStatus extends Enumeration {
	val ONLINE, OFFLINE, UNKNOWN = Value
}