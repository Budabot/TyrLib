package com.jkbff.ao.tyrlib.packets;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

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
        int length = 0;
        if (abstractTypeArray != null) {
            for (AbstractType type : abstractTypeArray) {
                length += type.size();
            }
        }
        outputStream.writeShort(length);

        // write payload
        if (abstractTypeArray != null) {
            for (AbstractType type : abstractTypeArray) {
                outputStream.write(type.getRaw());
            }
        }

        return byteStream.toByteArray();
    }

    @Override
    public String toString() {

        String output = new StringBuffer()
            .append(getPacketType()).append(" ").append(" (").append(this.getClass().getName()).append(")")
            .toString();

        return output;
    }
}
