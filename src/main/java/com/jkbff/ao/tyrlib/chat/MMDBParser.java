package com.jkbff.ao.tyrlib.chat;

import no.geosoft.cc.util.ByteSwapper;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class MMDBParser {
	public static String getMessage(long categoryId, long instanceId) {
		RandomAccessFile in = null;
		
		try {
			in = new RandomAccessFile(new File(MMDBParser.class.getResource("/text.mdb").toURI()), "r");
			
			Entry category = findEntry(in, categoryId, 8);
			if (category == null) {
				throw new RuntimeException("Unknown Reference Type -- Could not find category. categoryId: '" + categoryId + "' instanceId: '" + instanceId + "'");
			}
			
			Entry instance = findEntry(in, instanceId, category.offset);
			if (instance == null) {
				throw new RuntimeException("Unknown Reference Type -- Could not find instance. categoryId: '" + categoryId + "' instanceId: '" + instanceId + "'");
			}
			
			in.seek(instance.offset);
			String message = readString(in);
			return message;
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw new RuntimeException("CategoryId: '" + categoryId + "' InstanceId: '" + instanceId + "'", e);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
		}
	}
	
	public static Entry readEntry(RandomAccessFile in) throws IOException {
		Entry entry = new Entry();
		entry.entryId = ByteSwapper.swap(in.readInt());
		entry.offset = ByteSwapper.swap(in.readInt());
		
		return entry;
	}
	
	public static Entry findEntry(RandomAccessFile in, long entryId, long offset) throws IOException {
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

	public static List<Entry> getAllEntries(RandomAccessFile in, long offset) throws IOException {
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
	
	public static String readString(RandomAccessFile in) throws IOException {
		String message = "";
		char character;
		
		character = (char)in.readByte();
		while (character != 0) {
			message += character;
			character = (char)in.readByte();
		}
		
		return message;
	}
	
	public static class Entry {
		public long entryId;
		public long offset;
	}
}
