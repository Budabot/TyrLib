package com.jkbff.ao.tyrlib.chat;

import no.geosoft.cc.util.ByteSwapper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class MMDBParser {
	private final RandomAccessFile in;

	public static MMDBParser createInstanceFromClasspath() {
		try {
			return new MMDBParser(new RandomAccessFile(new File(MMDBParser.class.getResource("/text.mdb").toURI()), "r"));
		} catch (FileNotFoundException | URISyntaxException e) {
			throw new RuntimeException(e);
		}
	}

	public MMDBParser(RandomAccessFile in) {
		this.in = in;
	}

	public String getMessage(long categoryId, long instanceId) {
		try {
			Entry category = findEntry(in, categoryId, 8);
			if (category == null) {
				throw new RuntimeException("Unknown Reference Type -- Could not find category. categoryId: '" + categoryId + "' instanceId: '" + instanceId + "'");
			}
			
			Entry instance = findEntry(in, instanceId, category.offset);
			if (instance == null) {
				throw new RuntimeException("Unknown Reference Type -- Could not find instance. categoryId: '" + categoryId + "' instanceId: '" + instanceId + "'");
			}
			
			in.seek(instance.offset);
			return readString(in);
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw new RuntimeException("CategoryId: '" + categoryId + "' InstanceId: '" + instanceId + "'", e);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (Exception e) {
					// ignore exceptions here
				}
			}
		}
	}
	
	public Entry readEntry(RandomAccessFile in) throws IOException {
		Entry entry = new Entry();
		entry.entryId = ByteSwapper.swap(in.readInt());
		entry.offset = ByteSwapper.swap(in.readInt());
		
		return entry;
	}
	
	public Entry findEntry(RandomAccessFile in, long entryId, long offset) throws IOException {
		in.seek(offset);

		long previousId = -1;
		Entry currentEntry = readEntry(in);
		while (currentEntry.entryId > previousId) {
			if (currentEntry.entryId == entryId) {
				return currentEntry;
			}

			previousId = currentEntry.entryId;
			currentEntry = readEntry(in);
		}

		return null;
	}

	public List<Entry> getAllEntries(RandomAccessFile in, long offset) throws IOException {
		in.seek(offset);

		List<Entry> entries = new ArrayList<Entry>();

		long previousId = -1;
		Entry currentEntry = readEntry(in);
		while (currentEntry.entryId > previousId) {
			entries.add(currentEntry);
			previousId = currentEntry.entryId;
			currentEntry = readEntry(in);
		}

		return entries;
	}
	
	public String readString(RandomAccessFile in) throws IOException {
		String message = "";
		char character;
		
		character = (char)in.readByte();
		while (character != 0) {
			message += character;
			character = (char)in.readByte();
		}
		
		return message;
	}
	
	public class Entry {
		public long entryId;
		public long offset;
	}
}
