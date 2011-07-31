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

public class CharacterId extends AbstractType {
	
	protected long mydata;

	public CharacterId() {
		mydata = 0;
	}

	public CharacterId(long i) {
		mydata = i;
	}

	public CharacterId(DataInputStream input) {
		try {
			byte[] data = new byte[size()];
			input.readFully(data, 0, size());
			mydata = Helper.bytesToLong(data);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public boolean equals(long i) {
		return mydata == i;
	}

	public boolean equals(CharacterId i) {
		return mydata == i.mydata;
	}

	public long getLongData() {
		return mydata;
	}

	public byte[] getRaw() {
		byte[] ret = new byte[4];
		Helper.integerToBytes(mydata, ret, 4, 0);
		return ret;
	}

	public int size() {
		return 4;
	}

	@Override
	public String toString() {
		return String.valueOf(mydata);
	}
}
