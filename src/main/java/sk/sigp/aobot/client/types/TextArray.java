package sk.sigp.aobot.client.types;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

public class TextArray extends AbstractType {
    private final Text[] data;

    public TextArray(String[] arr) {
        this.data = new Text[arr.length];
        for (int i = 0; i < arr.length; i++) {
            this.data[i] = new Text(arr[i]);
        }
    }

    public TextArray(DataInputStream input) {
        try {
            int size = input.readUnsignedShort();
            this.data = new Text[size];
            for(int i = 0; i < size; i++) {
                this.data[i] = new Text(input);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Text[] getData() {
        return data;
    }

    @Override
    public byte[] getBytes() {
        try {
            ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
            DataOutputStream outputStream = new DataOutputStream(byteStream);
            outputStream.writeShort(data.length);
            for (Text obj : data) {
                outputStream.write(obj.getBytes());
            }
            return byteStream.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return "[" + Arrays.stream(data).map(Text::toString).collect(Collectors.joining(", ")) + "]";
    }
}
