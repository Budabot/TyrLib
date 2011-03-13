package com.jkbff.ao.tyrlib.packets;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

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
		this.message = MMDBParser.getMessage(categoryId, instanceId);
		
		try {
			params = parseParams(new DataInputStream(new ByteArrayInputStream(paramString.getBytes("UTF-8"))));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public ExtendedMessage(DataInputStream dataInputStream) {
		this.categoryId = b85g(dataInputStream);
		this.instanceId = b85g(dataInputStream);
		this.message = MMDBParser.getMessage(categoryId, instanceId);

		try {
			params = parseParams(dataInputStream);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	private static List<Object> parseParams(DataInputStream dataInputStream) throws IOException {
		List<Object> params = new ArrayList<Object>();

		while (dataInputStream.available() > 0) {
			char paramType = (char)dataInputStream.read();
			switch (paramType) {
				case 'R':
					// reference
					params.add(MMDBParser.getMessage(b85g(dataInputStream), b85g(dataInputStream)));
					break;

				case 'i':
				case 'u':
					// long
					params.add(b85g(dataInputStream));
					break;

				case 'S':
					// string
					int stringLength = dataInputStream.readShort();
					byte[] bytes = new byte[stringLength];
					dataInputStream.readFully(bytes);
					
					params.add(new String(bytes, ENCODING));
					break;

				default:
					throw new RuntimeException("Unknown param type in extended message: '" + paramType + "'");
			}
		}
		
		return params;
	}
	
	public static long b85g(InputStream input) {
		try {
			long n = 0;
			for (int i = 0; i < 5;  i++) {
				n = (n * 85) + input.read() - 33;
			}
			return n;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
    }

	public String getFormattedMessage() {
		return String.format(message, params.toArray());
	}
	
	public String toString() {
		return getFormattedMessage();
	}

	public long getCategoryId() { return categoryId; }
	public long getInstanceId() { return instanceId; }
	public String getMessage() { return message; }
}
