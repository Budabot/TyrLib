package com.jkbff.ao.tyrlib.chat;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import aoChatLib.Crypto;

import com.jkbff.ao.tyrlib.packets.BaseClientPacket;
import com.jkbff.ao.tyrlib.packets.BaseServerPacket;
import com.jkbff.ao.tyrlib.packets.client.LoginRequest;
import com.jkbff.ao.tyrlib.packets.client.LoginSelect;
import com.jkbff.ao.tyrlib.packets.client.Ping;
import com.jkbff.ao.tyrlib.packets.server.CharacterList;
import com.jkbff.ao.tyrlib.packets.server.FriendUpdate;
import com.jkbff.ao.tyrlib.packets.server.LoginError;
import com.jkbff.ao.tyrlib.packets.server.LoginOk;
import com.jkbff.ao.tyrlib.packets.server.LoginSeed;

public class AOSingleConnection extends Thread implements AOConnection {

    private ChatPacketListener chatPacketListener;
    private ChatPacketSender chatPacketSender;
    private ChatPacketHandler chatPacketHandler;
    private String username;
    private String password;
    private String character;
    private String serverName;
    private int portNumber;
    private Socket socket;
    private STATUS loginStatus = STATUS.WAITING_FOR_SEED;
    
    private Map<Long, Friend> friendlist = new HashMap<Long, Friend>();
    
    private long lastReceivedPing = 0;
    private static final String PING_PAYLOAD = "abcdefghijklmnopqrstuvwxyzabcdefghi";
    private static final int PING_INTERVAL = 60000;
    
    private Logger log = Logger.getLogger(this.getClass());
    
    volatile boolean shouldStop = false;

    enum STATUS {
        WAITING_FOR_SEED, WAITING_FOR_CHAR_LIST, WAITING_FOR_LOGIN_OK, LOGGED_ON
    };

    @Override
    public void run() {
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
            log.error("(UnknownHostException)Could not connect to chat server " + serverName + ":" + portNumber, e);
            shutdown();
        } catch (IOException e) {
        	log.error("(IOException)Could not connect to chat server " + serverName + ":" + portNumber, e);
        	shutdown();
        }
        
        // send pings periodically to keep the connection alive
        lastReceivedPing = System.currentTimeMillis();
        while (!shouldStop) {
        	if (loginStatus == STATUS.LOGGED_ON) {
        		sendPacket(new Ping(PING_PAYLOAD));
        	}

        	synchronized (this) {
        		try {
					this.wait(PING_INTERVAL);
				} catch (InterruptedException e) {
					log.error(e);
				}
        	}
        	
        	if (System.currentTimeMillis() - lastReceivedPing > (2 * PING_INTERVAL)) {
        		log.error("ping reply not received past two times");
        		shutdown();
        	}
        }
    }
    
    public void shutdown() {
    	log.info(character + " shutting down.");
    	
    	shouldStop = true;
    	synchronized (this) {
    		notify();
    	}
    	
    	// threads have two seconds to cleanup before the socket closes
    	try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			log.error(e);
		}
    	
    	try {
    		if (socket != null) {
    			socket.close();
    		}
    	} catch (IOException e) {
    		log.error(e);
    	}
    }

    void processIncomingPacket(BaseServerPacket packet) {
    	log.debug("SERVER " + packet);
    	
        // If logged on, dispatch packet to handlers, otherwise, complete login sequence
        if (loginStatus == STATUS.LOGGED_ON) {
        	if (packet instanceof com.jkbff.ao.tyrlib.packets.server.Ping) {
        		lastReceivedPing = System.currentTimeMillis();
        	}
        	if (packet instanceof FriendUpdate) {
        		FriendUpdate friendUpdate = (FriendUpdate)packet;
        		friendlist.put(friendUpdate.getUserId(), new Friend(friendUpdate.getUserId(), friendUpdate.getOnline() == 0 ? false : true, friendUpdate.getStatus()));
        	}
        	
            processPacket(packet);
        } else if (packet instanceof LoginSeed) {
            LoginSeed loginSeed = (LoginSeed) packet;

            String randomPrefix = Crypto.randomHexString(8);
            String loginString = username + "|" + loginSeed.getSeed() + "|" + password;

            String key = Crypto.generateKey(randomPrefix, loginString);

            LoginRequest loginRequest = new LoginRequest(0, username, key);
            sendPacket(loginRequest);
            loginStatus = STATUS.WAITING_FOR_CHAR_LIST;
        } else if (packet instanceof CharacterList) {
            CharacterList characterListPacket = (CharacterList) packet;

            Long userId = null;
            for (CharacterList.LoginUser loginUser : characterListPacket.getLoginUsers()) {
                if (character.equalsIgnoreCase(loginUser.getName())) {
                    userId = loginUser.getUserId();
                    break;
                }
            }

            if (userId == null) {
                throw new RuntimeException("Could not find character with name '" + character + "' on account '" + username + "'");
            }

            LoginSelect selectCharacterPacket = new LoginSelect(userId);
            sendPacket(selectCharacterPacket);
            loginStatus = STATUS.WAITING_FOR_LOGIN_OK;
        } else if (packet instanceof LoginOk) {
            loginStatus = STATUS.LOGGED_ON;

            // this is sent to dispatcher so logged in event can be handled
            processPacket(packet);
        } else if (packet instanceof LoginError) {
        	shutdown();
            throw new RuntimeException(((LoginError)packet).getMessage());
        }
    }
    
    public void processPacket(BaseServerPacket packet) {
   		chatPacketHandler.processPacket(packet, this);
    }

    public void sendPacket(BaseClientPacket packet) {
    	log.debug("CLIENT " + packet);
        chatPacketSender.sendPacket(packet);
    }

    public void setChatPacketHandler(ChatPacketHandler chatPacketHandler) {
    	this.chatPacketHandler = chatPacketHandler;
    }
    
    public Boolean isOnline(long charId) {
    	Friend friend = friendlist.get(charId);
    	if (friend == null) {
    		return null;
    	} else {
    		return friend.online;
    	}
    }
    
    public Map<Long, Friend> getFriendlist() { return friendlist; }
    
    public String getCharacter() { return character; }

    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }
    public void setCharacter(String character) { this.character = character; }
    public void setServerName(String serverName) { this.serverName = serverName; }
    public void setPortNumber(int portNumber) { this.portNumber = portNumber; }
    
    private class Friend {
    	private long charid;
    	private boolean online;
    	private String status;
    	
    	private Friend(long charid, boolean online, String status) {
    		this.charid = charid;
    		this.online = online;
    		this.status = status;
    	}
    }
}
