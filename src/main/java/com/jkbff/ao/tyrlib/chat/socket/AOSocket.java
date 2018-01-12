package com.jkbff.ao.tyrlib.chat.socket;

import com.jkbff.ao.tyrlib.packets.BasePacket;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class AOSocket<T extends BasePacket, U extends BasePacket> {
    private final Socket socket;
    private final PacketFactory<T> packetFactory;
    private final OutputStream outputStream;
    private final DataInputStream inputStream;

    public AOSocket(Socket socket, PacketFactory<T> packetFactory) {
        this.socket = socket;
        this.packetFactory = packetFactory;
        try {
            this.outputStream = socket.getOutputStream();
            this.inputStream = new DataInputStream(socket.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendPacket(U packet) {
        try {
            outputStream.write(packet.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // this blocks until data is available
    public T readPacket() throws IOException {
        // read the packet bytes from the stream
        int packetId = inputStream.readUnsignedShort();
        int packetLength = inputStream.readUnsignedShort();
        byte[] payload = new byte[packetLength];
        inputStream.readFully(payload);

        T packet = packetFactory.createInstance(packetId, payload);
        if (packet == null) {
            throw new RuntimeException("Unknown packet id: '" + packetId + "'\npacketLength: '" + packetLength + "'\npayload: '" + payload + "'");
        }
        return packet;
    }

    public void close() {
        try {
            inputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            outputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
