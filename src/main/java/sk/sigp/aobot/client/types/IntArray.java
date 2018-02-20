package sk.sigp.aobot.client.types;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

public class IntArray extends AbstractType {
    private final Int[] data;

    public IntArray(int[] arr) {
        this.data = new Int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            this.data[i] = new Int(arr[i]);
        }
    }

    public IntArray(DataInputStream input) {
        try {
            int size = input.readUnsignedShort();
            this.data = new Int[size];
            for(int i = 0; i < size; i++) {
                this.data[i] = new Int(input);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Int[] getData() {
        return data;
    }

    @Override
    public byte[] getBytes() {
        try {
            ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
            DataOutputStream outputStream = new DataOutputStream(byteStream);
            outputStream.writeShort(data.length);
            for (Int obj : data) {
                outputStream.write(obj.getBytes());
            }
            return byteStream.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return "[" + Arrays.stream(data).map(Int::toString).collect(Collectors.joining(", ")) + "]";
    }
}
