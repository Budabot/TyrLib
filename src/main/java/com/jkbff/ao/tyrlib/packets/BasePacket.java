package com.jkbff.ao.tyrlib.packets;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Stream;

import sk.sigp.aobot.client.types.AbstractType;

public abstract class BasePacket {

    public abstract int getPacketType();

    public abstract byte[] getBytes() throws IOException;

    protected byte[] getBytes(AbstractType... abstractTypeArray) throws IOException {

        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        DataOutputStream outputStream = new DataOutputStream(byteStream);

        // write packet type
        outputStream.writeShort(getPacketType());

        // write packet payload length
        if (abstractTypeArray != null) {
            final Stream<byte[]> bytesArrays = Arrays.stream(abstractTypeArray).map(AbstractType::getRaw);
            int size = bytesArrays.map(bytes -> bytes.length).reduce( (length1, length2) -> length1 + length2).orElse(0);

            // write size
            outputStream.writeShort(size);

            bytesArrays.forEach(bytes -> {
                try {
                    // write payload
                    outputStream.write(bytes);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }

        return byteStream.toByteArray();
    }

    @Override
    public String toString() {
        return String.valueOf(getPacketType()) + " " + " (" + this.getClass().getName() + ")";
    }
}
