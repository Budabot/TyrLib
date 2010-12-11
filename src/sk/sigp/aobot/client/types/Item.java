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


public class Item extends AbstractType {
	protected int lowID, highID, quality, dummy;

	public Item() {
		lowID = 0;
		highID = 0;
		quality = 0;
		dummy = 0;
	}

	public Item(int low, int high, int qual) {
		lowID = low;
		highID = high;
		quality = qual;
		dummy = 0;
	}

	public Item(DataInputStream input) throws IOException {
		lowID = input.readInt();
		highID = input.readInt();
		quality = input.readInt();
		dummy = input.readInt();
	}

	public Item(Raw d) {
		lowID = d.bytesToInt(2);
		highID = d.bytesToInt(6);
		quality = d.bytesToInt(10);
		dummy = d.bytesToInt(14);
	}

	public byte[] getRaw() {
		byte[] ret = new byte[20];
		integerToBytes(18, ret, 2, 0);
		integerToBytes(0, ret, 2, 2);
		integerToBytes(lowID, ret, 4, 4);
		integerToBytes(highID, ret, 4, 8);
		integerToBytes(quality, ret, 4, 12);
		integerToBytes(dummy, ret, 4, 16);
		return ret;
	}

	public int size() {
		return 20;
	}

        @Override
	public String toString() {
		return super.toString() + "( " + lowID + "; " + highID + "; " + quality
				+ "; " + dummy + " )";
	}
}
