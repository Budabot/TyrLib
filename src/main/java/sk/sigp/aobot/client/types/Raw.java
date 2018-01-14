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
import java.io.UnsupportedEncodingException;

import com.jkbff.ao.tyrlib.chat.Helper;


public class Raw extends AbstractType {
	protected final byte[] data;

	public Raw(byte[] d) {
		data = new byte[d.length];
		System.arraycopy(d, 0, data, 0, d.length);
	}

	public Raw(DataInputStream input) {
		try {
			int size = input.readUnsignedShort();
			data = new byte[size];
			input.readFully(data);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public Raw(String str) {
		try {
			data = str.getBytes(ENCODING);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

	public byte[] getData() {
		return data;
	}

	public byte[] getBytes() {
		byte ret[] = new byte[size()];
		Helper.integerToBytes(data.length, ret, 2, 0);
		Helper.copy(data, ret, 2);
		return ret;
	}

	public int size() {
		return data.length + 2;
	}

	@Override
	public String toString() {
		try {
			return new String(data, ENCODING);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
