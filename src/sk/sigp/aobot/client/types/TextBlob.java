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

import java.io.DataInputStream;
import java.io.IOException;


public class TextBlob extends AbstractType {
	protected String mydata;

	public TextBlob() {
		mydata = "";
	}

	public TextBlob(String str) {
		mydata = str;
	}

	public TextBlob(DataInputStream input) throws IOException {
		input.skipBytes(14);
		int size = input.readInt();
		byte[] bytes = new byte[size];
		input.readFully(bytes);
		try {
			mydata = new String(bytes, ENCODING);
		} catch (Exception e) {
			mydata = "";
		}
	}

	public TextBlob(Raw d) {
		mydata = d.bytesToString(16);
	}

	public String getStringData() {
		return mydata;
	}

	public byte[] getRaw() {
		byte[] str = new byte[0];
		try {
			str = mydata.getBytes(ENCODING);
		} catch (Exception e) {
		}
		byte[] raw = new byte[str.length + 20];

		integerToBytes(str.length + 18, raw, 2, 0);
		integerToBytes(0, raw, 2, 2);
		integerToBytes(0x0000C350, raw, 4, 4);
		integerToBytes(str.hashCode() & 0xFFFF, raw, 4, 8);
		integerToBytes(0, raw, 4, 12);
		integerToBytes(0, raw, 2, 16);
		integerToBytes(str.length, raw, 2, 18);
		copy(str, raw, 20);
		return raw;
	}

	public int size() {
		return getRaw().length;
	}

	@Override
	public String toString() {
		return mydata;
	}
}
