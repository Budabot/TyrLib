package com.jkbff.ao.tyrlib.packets;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;

import com.jkbff.ao.tyrlib.chat.Helper;
import com.jkbff.ao.tyrlib.chat.MMDBParser;

public class ExtendedMessageParser {

    public static final String ENCODING = "ISO-8859-1";
    private final MMDBParser mmdbParser;

    public ExtendedMessageParser(MMDBParser mmdbParser) {
        this.mmdbParser = mmdbParser;
    }

    public ExtendedMessage parse(long categoryId, long instanceId, String paramString) {
        try {
            String message = mmdbParser.getMessage(categoryId, instanceId);
            DataInputStream stream = new DataInputStream(new ByteArrayInputStream(paramString.getBytes("UTF-8")));
            List<Object> params = parseParams(stream);
            return new ExtendedMessage(categoryId, instanceId, message, params);
        } catch (Exception e) {
            throw new RuntimeException(String.format("for categoryId: '%d'; instanceId: '%d'; paramString: '%s'", categoryId, instanceId, paramString), e);
        }
    }

    public ExtendedMessage parse(DataInputStream dataInputStream) {
        long categoryId = Helper.b85g(dataInputStream);
        long instanceId = Helper.b85g(dataInputStream);

        try {
            String message = mmdbParser.getMessage(categoryId, instanceId);
            List<Object> params = parseParams(dataInputStream);
            return new ExtendedMessage(categoryId, instanceId, message, params);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Object> parseParams(DataInputStream dataInputStream) throws IOException {
        List<Object> params = new ArrayList<Object>();

        while (dataInputStream.available() > 0) {
            char paramType = (char) dataInputStream.read();
            switch (paramType) {
                case 'S':
                {
                    // long string
                    int stringLength = dataInputStream.readShort();
                    byte[] bytes = new byte[stringLength];
                    dataInputStream.readFully(bytes);
                    params.add(new String(bytes, ENCODING));
                    break;
                }

                case 's':
                {
                    // short string
                    int stringLength = dataInputStream.readUnsignedByte();
                    byte[] bytes = new byte[stringLength];
                    dataInputStream.readFully(bytes);
                    params.add(new String(bytes, ENCODING));
                    break;
                }

                case 'I':
                {
                    byte[] instanceBytes = new byte[4];
                    dataInputStream.readFully(instanceBytes);
                    params.add(Helper.bytesToLong(instanceBytes));
                    break;
                }

                case 'i':
                case 'u':
                {
                    // long
                    params.add(Helper.b85g(dataInputStream));
                    break;
                }

                case 'R':
                {
                    // reference
                    params.add(mmdbParser.getMessage(Helper.b85g(dataInputStream), Helper.b85g(dataInputStream)));
                    break;
                }

                case 'l':
                {
                    byte[] instanceBytes = new byte[4];
                    dataInputStream.readFully(instanceBytes);
                    long instanceId = Helper.bytesToLong(instanceBytes);
                    long categoryId = 20000;
                    params.add(mmdbParser.getMessage(categoryId, instanceId));
                    break;
                }

                default:
                    byte[] bytes64 = Base64.encodeBase64(IOUtils.toByteArray(dataInputStream));
                    throw new RuntimeException("Unknown param type in extended message: '" + paramType + "' with payload(base64): '" + new String(bytes64) + "'");
            }
        }

        return params;
    }
}
