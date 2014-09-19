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

public class ExtendedMessage {

	public static final String ENCODING = "ISO-8859-1";
	
	private long categoryId;
	private long instanceId;
	private String message;

	private List<Object> params;

	public ExtendedMessage(long categoryId, long instanceId, String paramString) {
		this.categoryId = categoryId;
		this.instanceId = instanceId;

		try {
			message = MMDBParser.getMessage(categoryId, instanceId);
			params = parseParams(new DataInputStream(new ByteArrayInputStream(paramString.getBytes("UTF-8"))));
		} catch (Exception e) {
			throw new RuntimeException(String.format("for categoryId: '%d'; instanceId: '%d'; paramString: '%s'", categoryId, instanceId, paramString), e);
		}
	}

	public ExtendedMessage(DataInputStream dataInputStream) {
		this.categoryId = Helper.b85g(dataInputStream);
		this.instanceId = Helper.b85g(dataInputStream);

		try {
			message = MMDBParser.getMessage(categoryId, instanceId);
			params = parseParams(dataInputStream);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static List<Object> parseParams(DataInputStream dataInputStream) throws IOException {
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
					params.add(MMDBParser.getMessage(Helper.b85g(dataInputStream), Helper.b85g(dataInputStream)));
					break;
				}

				case 'l':
				{
					byte[] instanceBytes = new byte[4];
					dataInputStream.readFully(instanceBytes);
					long instanceId = Helper.bytesToLong(instanceBytes);
					long categoryId = 20000;
					params.add(MMDBParser.getMessage(categoryId, instanceId));
					break;
				}

				default:
					byte[] bytes64 = Base64.encodeBase64(IOUtils.toByteArray(dataInputStream));
					throw new RuntimeException("Unknown param type in extended message: '" + paramType + "' with payload(base64): '" + new String(bytes64) + "'");
			}
		}

		return params;
	}

	public String getFormattedMessage() {
		return String.format(message, params.toArray());
	}

	public String toString() {
		return getFormattedMessage();
	}

	public long getCategoryId() {
		return categoryId;
	}

	public long getInstanceId() {
		return instanceId;
	}

	public String getMessage() {
		return message;
	}
}
