This is an implementation of the Anarchy Online Chat Protocol, written in Java.

# Sample Bot

```
package com.example;

import aoChatLib.Crypto;
import com.jkbff.ao.tyrlib.chat.socket.AOClientSocket;
import com.jkbff.ao.tyrlib.chat.socket.Closeable;
import com.jkbff.ao.tyrlib.packets.serialization.ClientPacketSerializer;
import com.jkbff.ao.tyrlib.packets.client.LoginRequest;
import com.jkbff.ao.tyrlib.packets.client.LoginSelect;
import com.jkbff.ao.tyrlib.packets.serialization.ServerPacketDeserializer;
import com.jkbff.ao.tyrlib.packets.server.*;

import java.net.Socket;

public class LoginExample {

    private boolean isRunning = true;

    private String username = "username";
    private String password = "password";
    private String characterName = "character";

    public static void main(String[] args) throws Exception {
        new LoginExample().start();
    }

    public void start() throws Exception {
        // set server address and port
        String serverAddress = "chat.d1.funcom.com";
        int serverPort = 7105;

        // the library needs to know how to create incoming packets from bytes (deserializer), and how to convert
        // outgoing packets to bytes (serializer).  the library includes an implementation to do this, but you can
        // provide your own serializer/deserializer (and packet implementations) if desired
        ClientPacketSerializer serializer = new ClientPacketSerializer();
        ServerPacketDeserializer deserializer = new ServerPacketDeserializer();


        // we need a class with a close() method that will be called when there is a socket/connection error
        // so we can cleanup resources.  in this example, it just sets isRunning flag to false so the connection
        // will shut down gracefully
        Closeable onError = new SocketError(this);

        // create a client socket (a bot will act as a client to the ao chat server)
        AOClientSocket aoClientSocket = new AOClientSocket("main", new Socket(serverAddress, serverPort), deserializer, serializer, onError);

        // connect socket to ao chat server
        aoClientSocket.start();

        while (isRunning) {
            // read packet from socket, packet will wait up to 1s for a packet, and then will return null
            // this is then done in a loop, to allow graceful shutdown when isRunning is flipped to false
            BaseServerPacket packet = aoClientSocket.readPacket();
            if (packet != null) {
                // print out packet for debug purposes
                System.out.println(packet);

                // get type of packet based on the packet type
                switch (packet.getPacketType()) {
                    case LoginSeed.TYPE:
                        // cast to correct packet type
                        LoginSeed loginSeed = (LoginSeed) packet;

                        // generate login key from username, password, and seed using AO Crypto class
                        String loginKey = Crypto.generateKey(username, password, loginSeed.getSeed());

                        // create LoginRequest packet
                        LoginRequest loginRequest = new LoginRequest(0, username, loginKey);

                        // send packet (this should take a generic type, and allow an outgoing packet factory for
                        // converting a packet to bytes but this has not been done yet)
                        aoClientSocket.sendPacket(loginRequest);
                        break;
                    case LoginError.TYPE:
                        // cast to correct packet type
                        LoginError loginError = (LoginError) packet;

                        System.out.println("error logging in: " + loginError.getMessage());
                        this.stop();
                        break;
                    case CharacterList.TYPE:
                        // cast to correct packet type
                        CharacterList characterList = (CharacterList) packet;

                        // find character id for the character we want to login as
                        long charId = -1;
                        for (int i = 0; i < characterList.getName().length; i++) {
                            if (characterList.getName()[i].getData().equals(characterName)) {
                                charId = characterList.getUserId()[i].getData();
                            }
                        }

                        if (charId == -1) {
                            System.out.println("Could not find character '" + characterName + "' on account");
                            this.stop();
                        } else {
                            LoginSelect loginSelect = new LoginSelect(charId);
                            aoClientSocket.sendPacket(loginSelect);
                        }
                        break;
                    case LoginOk.TYPE:
                        System.out.println("Login successful!");
                        break;
                    // TODO handle other packets
                    default:
                        // do nothing
                }
            }
        }

        System.out.println("bot is shutdown");
    }

    public void stop() {
        this.isRunning = false;
    }
}


class SocketError implements Closeable {
    private LoginExample loginExample;

    public SocketError(LoginExample loginExample) {
        this.loginExample = loginExample;
    }

    @Override
    public void close() {
        // cleanup any resources (eg. threadpools, file pointers, database connections, etc)

        // shutdown bot gracefull
        this.loginExample.stop();
    }
}
```