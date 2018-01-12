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


public class Int extends AbstractType {
	protected final int data;

	public Int(int i) {
		data = i;
	}

	public Int(DataInputStream input) {
		try {
			data = input.readInt();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public int getData() {
		return data;
	}

	public byte[] getRaw() {
		byte[] ret = new byte[size()];
		Helper.integerToBytes(data, ret, size(), 0);
		return ret;
	}

	public int size() {
		return 4;
	}

    @Override
	public String toString() {
		return String.valueOf(data);
	}
}
