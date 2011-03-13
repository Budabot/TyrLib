package com.jkbff.ao.tyrlib.chat;

public class Helper {
	
	public static final int CHATGROUPIDSIZE = 5;
	
	public static String print(byte[] bytes) {
		StringBuilder returnValue = new StringBuilder();
		for (byte abyte: bytes) {
			returnValue.append((int)abyte  + " ");
		}
		return returnValue.toString();
	}
	
	public static <T> String print(T[] array) {
		StringBuilder str = new StringBuilder();
		for (T obj : array) {
			str.append(obj.toString() + " ");
		}
		return str.toString();
	}
	
	protected static void longToBytes(long from, byte[] to, int off) {
		
		to[off + 0] = (byte) ((from >> 24) & 0xFF);
		to[off + 1] = (byte) ((from >> 16) & 0xFF);
		to[off + 2] = (byte) ((from >> 8) & 0xFF);
		to[off + 3] = (byte) ((from >> 0) & 0xFF);
	}

	protected static void shortToBytes(int from, byte[] to, int off) {
		to[off + 0] = (byte) ((from >> 8) & 0xFF);
		to[off + 1] = (byte) ((from >> 0) & 0xFF);
	}
	
	public static void copy(byte[] from, byte[] to, int toff) {
		// stupid routine. make sure to.length >= from.length+toff
		for (int i = from.length - 1; i >= 0; i--)
			to[toff + i] = from[i];
	}

	public static void integerToBytes(long from, byte[] to, int length, int off) {
		for (int i = 0; i < length; i++) {
			int byteArraySizeInBits = length * Byte.SIZE;
			int bitShiftSize = byteArraySizeInBits - (8 * (i + 1));
			
			to[off + i] = (byte) ((from >> bitShiftSize) & 0xFF);
		}
	}
	
	public static long bytesToLong (byte[] bytes) {
		long newLong = 0;
		for (int i = 0; i < bytes.length; i++) {
			long tempLong = bytes[i];
			if (tempLong < 0) {
				tempLong += 256;
			}

			newLong += (tempLong << ((bytes.length - i - 1) * 8));
		}

		return newLong;
	}
	
	public static boolean isOrgChat(long chatGroupId) {
		if ((chatGroupId & 0xFF00000000L) >> 32 == 3) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean isOOC(long chatGroupId) {
		if ((chatGroupId & 0xFF) == 16) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean isJpnOOC(long chatGroupId) {
		if ((chatGroupId & 0xFF) == 17) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean isShopping100(long chatGroupId) {
		if ((chatGroupId & 0xFF) == 9) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isShopping50to100(long chatGroupId) {
		if ((chatGroupId & 0xFF) == 5) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean isShopping11to50(long chatGroupId) {
		if ((chatGroupId & 0xFF) == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean isShopping(long chatGroupId) {
		if ((chatGroupId & 0xFF00000000L) >> 32 == 134) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean isNotumWars(long chatGroupId) {
		if ((chatGroupId & 0xFF00000000L) >> 32 == 10) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean isOmni(long chatGroupId) {
		if ((chatGroupId & 0xFF00L) >> 8 == 2) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isNeut(long chatGroupId) {
		if ((chatGroupId & 0xFF00L) >> 8 == 0) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isClan(long chatGroupId) {
		if ((chatGroupId & 0xFF00L) >> 8 == 1) {
			return true;
		} else {
			return false;
		}
	}
}
