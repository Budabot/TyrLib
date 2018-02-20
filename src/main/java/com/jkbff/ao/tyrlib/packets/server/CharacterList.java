package com.jkbff.ao.tyrlib.packets.server;

import sk.sigp.aobot.client.types.*;

import java.io.DataInputStream;
import java.io.IOException;

public class CharacterList extends BaseServerPacket {
	
	public static final int TYPE = 7;

	protected final CharacterIdArray userId;
	protected final TextArray name;
	protected final IntArray level;
	protected final IntArray online;

	public CharacterList(DataInputStream input) {
		this.userId = new CharacterIdArray(input);
		this.name = new TextArray(input);
		this.level = new IntArray(input);
		this.online = new IntArray(input);
	}
	
	public CharacterList(long[] userId, String[] name, int[] level, int[] online) {
		this.userId = new CharacterIdArray(userId);
		this.name = new TextArray(name);
		this.level = new IntArray(level);
		this.online = new IntArray(online);
	}
	
	public int getPacketType() {
		return CharacterList.TYPE;
	}

	public CharacterId[] getUserId() {
		return userId.getData();
	}

	public Text[] getName() {
		return name.getData();
	}

	public Int[] getLevel() {
		return level.getData();
	}

	public Int[] getOnline() {
		return online.getData();
	}
	
	public LoginUser[] getLoginUsers() {
		LoginUser[] loginUsers = new LoginUser[getUserId().length];
		for (int i = 0; i < getUserId().length; i++) {
			loginUsers[i] = new LoginUser(getUserId()[i], getName()[i], getLevel()[i], getOnline()[i]);
		}
		
		return loginUsers;
	}
	
	public class LoginUser {
		private long userId;
		private String name;
		private int level;
		private int online;
		
		public LoginUser(CharacterId userId, Text name, Int level, Int online) {
			this.userId = userId.getData();
			this.name = name.getData();
			this.level = level.getData();
			this.online = online.getData();
		}

		public long getUserId() {
			return userId;
		}
		public String getName() {
			return name;
		}
		public int getLevel() {
			return level;
		}
		public int getOnline() {
			return online;
		}
	}

	@Override
	public AbstractType[] getParameters() {
		return new AbstractType[]{userId, name, level, online};
	}
}
