package com.jkbff.ao.tyrlib.chat;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import aoChatLib.Crypto;

import com.jkbff.ao.tyrlib.packets.BaseClientPacket;
import com.jkbff.ao.tyrlib.packets.BaseServerPacket;
import com.jkbff.ao.tyrlib.packets.client.LoginRequestPacket;
import com.jkbff.ao.tyrlib.packets.client.LoginSelectCharacterPacket;
import com.jkbff.ao.tyrlib.packets.client.PingPacket;
import com.jkbff.ao.tyrlib.packets.server.LoginCharacterListPacket;
import com.jkbff.ao.tyrlib.packets.server.LoginErrorPacket;
import com.jkbff.ao.tyrlib.packets.server.LoginOkPacket;
import com.jkbff.ao.tyrlib.packets.server.LoginSeedPacket;

public class AOBot {

    private ChatPacketListener chatPacketListener;
    private ChatPacketSender chatPacketSender;
    private ChatPacketHandler chatPacketHandler;
    private String username;
    private String password;
    private String characterName;
    private String serverName;
    private int portNumber;
    private Socket socket;
    private STATUS loginStatus = STATUS.WAITING_FOR_SEED;
    
    volatile boolean shouldStop = false;
    
    private static final int FIVE_MINUTES = 300000;

    enum STATUS {
        WAITING_FOR_SEED, WAITING_FOR_CHAR_LIST, WAITING_FOR_LOGIN_OK, LOGGED_ON
    };

    public void init() {
        try {
            socket = new Socket(serverName, portNumber);

            chatPacketListener = new ChatPacketListener();
            chatPacketListener.setInputStream(socket.getInputStream());
            chatPacketListener.setAOBot(this);
            chatPacketListener.setName("chatPacketListener");
            chatPacketListener.start();

            chatPacketSender = new ChatPacketSender();
            chatPacketSender.setOutputStream(socket.getOutputStream());
            chatPacketSender.setAOBot(this);
            chatPacketSender.setName("chatPacketSender");
            chatPacketSender.start();
        } catch (UnknownHostException e) {
        	shutdown();
            throw new RuntimeException("(UnknownHostException)Could not connect to chat server " + serverName + ":" + portNumber, e);
        } catch (IOException e) {
        	shutdown();
            throw new RuntimeException("(IOException)Could not connect to chat server " + serverName + ":" + portNumber, e);
        }
        
        // send pings every 5 minutes to keep the connection alive
        while (!shouldStop) {
        	if (loginStatus == STATUS.LOGGED_ON) {
        		chatPacketSender.sendPacket(new PingPacket("abcdefghijklmnopqrstuvwxyzabcdefghi"));
        	}

        	synchronized (this) {
        		try {
					this.wait(FIVE_MINUTES);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
        	}
        }
    }
    
    public void shutdown() {
    	shouldStop = true;
    	synchronized (this) {
    		notify();
    	}
    	
    	// threads have five seconds to cleanup before the socket closes
    	try {
			Thread.sleep(5000);
		} catch (InterruptedException e1) {

		}
    	
    	try {
    		if (socket != null) {
    			socket.close();
    		}
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }

    void processIncomingPacket(BaseServerPacket packet) throws Exception {
    	packet.setAOBot(this);
    	
        // If logged on, dispatch packet to handlers, otherwise, complete login sequence
        if (loginStatus == STATUS.LOGGED_ON) {
            processPacket(packet);
        } else if (packet.getPacketType() == LoginSeedPacket.TYPE) {
            LoginSeedPacket loginSeedPacket = (LoginSeedPacket) packet;

            String randomPrefix = Crypto.randomHexString(8);
            String loginString = username + "|" + loginSeedPacket.getSeed() + "|" + password;

            String key = Crypto.generateKey(randomPrefix, loginString);

            LoginRequestPacket loginRequestPacket = new LoginRequestPacket(0, username, key);
            chatPacketSender.sendPacket(loginRequestPacket);
            loginStatus = STATUS.WAITING_FOR_CHAR_LIST;
        } else if (packet.getPacketType() == LoginCharacterListPacket.TYPE) {
            LoginCharacterListPacket characterListPacket = (LoginCharacterListPacket) packet;

            Long userId = null;
            for (LoginCharacterListPacket.LoginUser loginUser : characterListPacket.getLoginUsers()) {
                if (characterName.equalsIgnoreCase(loginUser.getName())) {
                    userId = loginUser.getUserId();
                    break;
                }
            }

            if (userId == null) {
                throw new RuntimeException("Could not find character with name '" + characterName + "' on account '" + username + "'");
            }

            LoginSelectCharacterPacket selectCharacterPacket = new LoginSelectCharacterPacket(userId);
            chatPacketSender.sendPacket(selectCharacterPacket);
            loginStatus = STATUS.WAITING_FOR_LOGIN_OK;
        } else if (packet.getPacketType() == LoginOkPacket.TYPE) {
            loginStatus = STATUS.LOGGED_ON;

            // this is sent to dispatcher so logged in event can be handled
            processPacket(packet);
        } else if (packet.getPacketType() == LoginErrorPacket.TYPE) {
            if (loginStatus == STATUS.WAITING_FOR_CHAR_LIST) {
                throw new RuntimeException("Server rejected login (username and/or password is incorrect)");
            } else if (loginStatus == STATUS.WAITING_FOR_LOGIN_OK) {
                throw new RuntimeException("Server rejected character selection");
            } else {
                throw new RuntimeException("unknown error occured during login; status: '" + loginStatus + "'");
            }
        }
    }
    
    public void processPacket(BaseServerPacket packet) {
    	try {
    		chatPacketHandler.processPacket(packet, this);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }

    public void sendPacket(BaseClientPacket packet) {
    	System.out.println("OUT " + packet);
        chatPacketSender.sendPacket(packet);
    }

    public void setChatPacketHandler(ChatPacketHandler chatPacketHandler) {
    	this.chatPacketHandler = chatPacketHandler;
    }

    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }
    public void setCharacterName(String characterName) { this.characterName = characterName; }
    public void setServerName(String serverName) { this.serverName = serverName; }
    public void setPortNumber(int portNumber) { this.portNumber = portNumber; }
}
