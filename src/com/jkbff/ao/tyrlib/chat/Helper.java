package com.jkbff.ao.tyrlib.chat;


public class Helper {
	
	public static final int CHATGROUPIDSIZE = 5;
	
	public static String printByteArray(byte[] bytes) {
		
		StringBuffer returnValue = new StringBuffer();
		for (byte abyte: bytes) {
			returnValue.append((int)abyte);
		}
		
		return returnValue.toString();
	}
	
	public static <T> String printArray(T[] array) {
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
	
	protected static long bytesTolong (byte[] bytes) {
		
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
		
		//if ((chatGroupId & 0xFF00000000L) >> 32 == 135) {
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
