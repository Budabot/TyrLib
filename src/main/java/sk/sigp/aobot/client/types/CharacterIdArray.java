package sk.sigp.aobot.client.types;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class CharacterIdArray extends AbstractType {
    private final CharacterId[] data;

    public CharacterIdArray(long[] arr) {
        this.data = new CharacterId[arr.length];
        for (int i = 0; i < arr.length; i++) {
            this.data[i] = new CharacterId(arr[i]);
        }
    }

    public CharacterIdArray(DataInputStream input) {
        try {
            int size = input.readUnsignedShort();
            this.data = new CharacterId[size];
            for(int i = 0; i < size; i++) {
                this.data[i] = new CharacterId(input);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public CharacterId[] getData() {
        return data;
    }

    @Override
    public byte[] getBytes() {
        try {
            ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
            DataOutputStream outputStream = new DataOutputStream(byteStream);
            outputStream.writeShort(data.length);
            for (CharacterId obj : data) {
                outputStream.write(obj.getBytes());
            }
            return byteStream.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return "[" + Arrays.stream(data).map(x -> x.toString() + ", ") + "]";
    }
}
