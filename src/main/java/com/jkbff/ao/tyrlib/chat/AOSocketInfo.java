package com.jkbff.ao.tyrlib.chat;

public class AOSocketInfo {
	public final String username;
    public final String password;
    public final String character;
    public final String server;
    public final int portNumber;
	
	public AOSocketInfo(String username, String password, String character, String server, int portNumber) {
    	this.username = username;
    	this.password = password;
    	this.character = character;
    	this.server = server;
    	this.portNumber = portNumber;
    }
}
