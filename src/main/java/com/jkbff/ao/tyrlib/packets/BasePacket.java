package com.jkbff.ao.tyrlib.packets;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import sk.sigp.aobot.client.types.AbstractType;

public abstract class BasePacket {

    public abstract int getPacketType();

    public abstract byte[] getBytes() throws IOException;

    protected byte[] getBytes(AbstractType... abstractTypeArray) throws IOException {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        DataOutputStream outputStream = new DataOutputStream(byteStream);

        // write packet type
        outputStream.writeShort(getPacketType());

        if (abstractTypeArray == null) {
            // write size of 0
            outputStream.writeShort(0);
        } else {
            // temporary output stream to hold types bytes from types so the size can be counted
            ByteArrayOutputStream s = new ByteArrayOutputStream();
            for (AbstractType abstractType : abstractTypeArray) {
                s.write(abstractType.getBytes());
            }

            outputStream.writeShort(s.size());
            outputStream.write(s.toByteArray());
        }

        return byteStream.toByteArray();
    }

    @Override
    public String toString() {
        return String.valueOf(getPacketType()) + " " + " (" + this.getClass().getName() + ")";
    }
}
