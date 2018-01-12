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

import com.jkbff.ao.tyrlib.chat.Helper;


public class TextBlob extends AbstractType {
	protected final String data;

	public TextBlob(String str) {
		data = str;
	}

	public TextBlob(DataInputStream input) {
		try {
			input.skipBytes(14);
			int size = input.readInt();
			byte[] bytes = new byte[size];
			input.readFully(bytes);
			data = new String(bytes, ENCODING);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public String getData() {
		return data;
	}

	public byte[] getRaw() {
		byte[] str = new byte[0];
		try {
			str = data.getBytes(ENCODING);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		byte[] raw = new byte[str.length + 20];

		Helper.integerToBytes(str.length + 18, raw, 2, 0);
		Helper.integerToBytes(0, raw, 2, 2);
		Helper.integerToBytes(0x0000C350, raw, 4, 4);
		Helper.integerToBytes(str.hashCode() & 0xFFFF, raw, 4, 8);
		Helper.integerToBytes(0, raw, 4, 12);
		Helper.integerToBytes(0, raw, 2, 16);
		Helper.integerToBytes(str.length, raw, 2, 18);
		Helper.copy(str, raw, 20);
		return raw;
	}

	@Override
	public String toString() {
		return data;
	}
}
