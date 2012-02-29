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

public class ChatGroupId extends AbstractType {
	
	protected long mydata;

	public ChatGroupId() {
		mydata = 0;
	}

	public ChatGroupId(long i) {
		mydata = i;
	}

	public ChatGroupId(DataInputStream input) {
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

	public boolean equals(ChatGroupId i) {
		return mydata == i.mydata;
	}

	public long getLongData() {
		return mydata;
	}

	public byte[] getRaw() {
		byte[] ret = new byte[5];
		Helper.integerToBytes(mydata, ret, 5, 0);
		return ret;
	}

	public int size() {
		return 5;
	}

	@Override
	public String toString() {
		return String.valueOf(mydata);
	}
	
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		} else if (!(obj instanceof ChatGroupId)) {
			return false;
		} else {
			return mydata == ((ChatGroupId)obj).mydata;
		}
	}
	
	public boolean isGuildChannel() {
		if ((mydata & 0xFF00000000L) >> 32 == 3) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isOOC() {
		//if ((chatGroupId & 0xFF00000000L) >> 32 == 135) {
		if ((mydata & 0xFF) == 16) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isJpnOOC() {
		if ((mydata & 0xFF) == 17) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isShopping100() {
		if ((mydata & 0xFF) == 9) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isShopping50to100() {
		if ((mydata & 0xFF) == 5) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isShopping11to50() {
		if ((mydata & 0xFF) == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isShopping() {
		if ((mydata & 0xFF00000000L) >> 32 == 134) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isNotumWars() {
		if ((mydata & 0xFF00000000L) >> 32 == 10) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isOmni() {
		if ((mydata & 0xFF00L) >> 8 == 2) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isNeut() {
		if ((mydata & 0xFF00L) >> 8 == 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isClan() {
		if ((mydata & 0xFF00L) >> 8 == 1) {
			return true;
		} else {
			return false;
		}
	}
}
