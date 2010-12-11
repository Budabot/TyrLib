/**
 *  Copyright 2002 Quaseem
 *  Copyright 2004 Migisan
 *  Copyright 2008 Oest
 *
 *  This file is part of the aochat package.
 *
 *  The aochat package is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  The aochat package is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with the aochat package; if not, write to the Free Software
 *  Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 */

package sk.sigp.aobot.client.types;

import java.io.*;

public abstract class AbstractType {
	
	public static final String ENCODING = "ISO-8859-1";
	
	public abstract byte[] getRaw();

	public abstract int size();

        @Override
	public String toString() {
		String s = this.getClass().getName();
		int i = s.indexOf(".");
		if (i > 0)
			s = s.substring(i + 1);
		return s;
	}

	public void write(DataOutputStream output) throws IOException {
		output.write(getRaw());
	}

	protected static void copy(byte[] from, byte[] to, int toff) {
		// stupid routine. make sure to.length >= from.length+toff
		for (int i = from.length - 1; i >= 0; i--)
			to[toff + i] = from[i];
	}

	protected static void integerToBytes(long from, byte[] to, int length, int off) {

                for (int i = 0; i < length; i++) {

			int byteArraySizeInBits = length * Byte.SIZE;
			int bitShiftSize = byteArraySizeInBits - (8 * (i + 1));
			
			to[off + i] = (byte) ((from >> bitShiftSize) & 0xFF);
		}
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
}
