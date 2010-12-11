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


/**
 * we don't support map data. this is a dummy class.
 */
public class Map extends AbstractType {
	protected static byte[] nulldata = new byte[] { 0 };

	public Map() {
	}

	public Map(DataInputStream input) throws IOException {
		int size = input.readUnsignedByte();
		for (int i = 0; i < size; i++) {
			int s = input.readUnsignedByte();
			int i5 = (s & 0xf000) >>> 12;
			int j5 = s & 0xfff;
			input.skipBytes(i5 + j5);
		}
	}

	public byte[] getRaw() {
		return nulldata;
	}

	public int size() {
		return 1;
	}

	public String toString() {
		return super.toString() + "( 0 )";
	}
}
