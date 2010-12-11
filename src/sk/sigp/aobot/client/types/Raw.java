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


public class Raw extends AbstractType {
	public byte[] mydata;

	public Raw() {
		mydata = new byte[]{0};
	}

	public Raw(byte[] d) {
		mydata = new byte[d.length];
		for (int i = d.length - 1; i >= 0; i--)
			mydata[i] = d[i];
	}

	public Raw(DataInputStream input) throws IOException {
		int size = input.readUnsignedShort();
		mydata = new byte[size];
		input.readFully(mydata);
	}

	public Raw(Item item) {
		mydata = new byte[16];
		integerToBytes(item.lowID, mydata, 4, 0);
		integerToBytes(item.highID, mydata, 4, 4);
		integerToBytes(item.quality, mydata, 4, 8);
		integerToBytes(item.dummy, mydata, 4, 12);
	}

	public Raw(String textblob) {
		byte[] str = new byte[0];
		try {
			str = textblob.getBytes(ENCODING);
		} catch (Exception e) {
		}
		mydata = new byte[str.length + 16];

		integerToBytes(0x0000C350, mydata, 4, 0);
		integerToBytes(str.hashCode() & 0xFFFF, mydata, 4, 4);
		integerToBytes(0, mydata, 4, 8);
		integerToBytes(0, mydata, 2, 12);
		integerToBytes(str.length, mydata, 2, 14);
		copy(str, mydata, 16);
	}

	public void append(Raw raw) {
		byte[] b = new byte[mydata.length + raw.mydata.length];
		for (int i = mydata.length - 1; i >= 0; i--)
			b[i] = mydata[i];
		for (int i = b.length - 1, j = raw.mydata.length - 1; j >= 0; i--, j--)
			b[i] = raw.mydata[j];
		mydata = b;
	}

	public byte[] getRaw() {
		byte ret[] = new byte[mydata.length + 2];
		integerToBytes(mydata.length, ret, 2, 0);
		copy(mydata, ret, 2);
		return ret;
	}

	public int size() {
		return mydata.length + 2;
	}

        @Override
	public String toString() {

		String str = super.toString() + "(" + getStringData() + " )";
		return str;
	}
	
	public String getStringData() {

		char c = 10;
		String str = "'";
		for (int i = 0, lcnt = 0; i < mydata.length; i++, lcnt++) {
			if (lcnt == 0) {
				if (mydata.length < 12)
					str += " ";
				else
					str += c + "          ";
			} else
				str += " ";
			String x = "0000" + Integer.toHexString((int) mydata[i]);
			str += x.substring(x.length() - 2);
			if (lcnt == 15)
				lcnt = -1;
		}
		str += "'";
		
		return str;
	}

	public int bytesToShort(int offset) {
		return ((mydata[offset] & 0xFF) << 8) + (mydata[offset + 1] & 0xFF);
	}

	public int bytesToInt(int offset) {
		return ((mydata[offset] & 0xFF) << 24)
				+ ((mydata[offset + 1] & 0xFF) << 16)
				+ ((mydata[offset + 2] & 0xFF) << 8)
				+ (mydata[offset + 3] & 0xFF);
	}

	public String bytesToString(int offset) {
		int size = bytesToShort(offset);
		try {
			String ret = new String(mydata, offset + 2, size, ENCODING);
			return ret;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
}
