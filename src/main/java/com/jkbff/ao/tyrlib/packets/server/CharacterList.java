package com.jkbff.ao.tyrlib.packets.server;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import sk.sigp.aobot.client.types.AbstractType;
import sk.sigp.aobot.client.types.Int;
import sk.sigp.aobot.client.types.Text;
import sk.sigp.aobot.client.types.CharacterId;

public class CharacterList extends BaseServerPacket {
	
	public static final int TYPE = 7;
	
	private CharacterId[] userId;
	private Text[] name;
	private Int[] level;
	private Int[] online;

	public CharacterList(DataInputStream input) {
		try {
			int userIdSize = input.readUnsignedShort();
			this.userId = new CharacterId[userIdSize];
			for(int i = 0; i < userIdSize; i++) {
				this.userId[i] = new CharacterId(input);
			}
			
			int nameSize = input.readUnsignedShort();
			this.name = new Text[nameSize];
			for(int i = 0; i < nameSize; i++) {
				this.name[i] = new Text(input);
			}
			
			int levelSize = input.readUnsignedShort();
			this.level = new Int[levelSize];
			for(int i = 0; i < levelSize; i++) {
				this.level[i] = new Int(input);
			}
			
			int onlineSize = input.readUnsignedShort();
			this.online = new Int[onlineSize];
			for(int i = 0; i < onlineSize; i++) {
				this.online[i] = new Int(input);
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public CharacterList(long[] userId, String[] name, int[] level, int[] online) {
		this.userId = new CharacterId[userId.length];
		for(int i = 0; i < userId.length; i++) {
			this.userId[i] = new CharacterId(userId[i]);
		}
		
		this.name = new Text[name.length];
		for(int i = 0; i < name.length; i++) {
			this.name[i] = new Text(name[i]);
		}
		
		this.level = new Int[level.length];
		for(int i = 0; i < level.length; i++) {
			this.level[i] = new Int(level[i]);
		}
		
		this.online = new Int[online.length];
		for(int i = 0; i < online.length; i++) {
			this.online[i] = new Int(online[i]);
		}
	}
	
	public int getPacketType() {
		return CharacterList.TYPE;
	}
	
	public byte[] getBytes() throws IOException {
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		DataOutputStream outputStream = new DataOutputStream(byteStream);
		
		// write packet type
		outputStream.writeShort(getPacketType());
		
		// write packet payload length
		// +8 accounts for the 2 bytes that indicate the size of each array
		int length = 8;
		length += getAbstractArraySize(userId);
		length += getAbstractArraySize(name);
		length += getAbstractArraySize(level);
		length += getAbstractArraySize(online);
		
		outputStream.writeShort(length);
		
		// write payload
		
		// write array length
		outputStream.writeShort(userId.length);
		for (CharacterId anUserId : userId) {
			outputStream.write(anUserId.getBytes());
		}
		
		// write array length
		outputStream.writeShort(name.length);
		for (Text aName : name) {
			outputStream.write(aName.getBytes());
		}
		
		// write array length
		outputStream.writeShort(level.length);
		for (Int aLevel : level) {
			outputStream.write(aLevel.getBytes());
		}
		
		// write array length
		outputStream.writeShort(online.length);
		for (Int anOnline : online) {
			outputStream.write(anOnline.getBytes());
		}
		
		return byteStream.toByteArray();
	}
	
	private int getAbstractArraySize(AbstractType[] abstractTypes) {
		int size = 0;
		for (AbstractType abstractType: abstractTypes) {
			size += abstractType.getBytes().length;
		}
		
		return size;
	}

	public CharacterId[] getUserId() {
		return userId;
	}

	public Text[] getName() {
		return name;
	}

	public Int[] getLevel() {
		return level;
	}

	public Int[] getOnline() {
		return online;
	}
	
	public LoginUser[] getLoginUsers() {
		LoginUser[] loginUsers = new LoginUser[userId.length];
		for (int i = 0; i < userId.length; i++) {
			
			loginUsers[i] = new LoginUser(userId[i], name[i], level[i], online[i]);
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
	
	public String toString() {
		LoginUser[] loginUsers = getLoginUsers();
		
		StringBuffer output = new StringBuffer()
			.append(TYPE).append(" ").append(this.getClass().getSimpleName());
		
		int i = 0;
		for (LoginUser loginUser: loginUsers) {
			output.append("\n\tCount: ").append(i++)
				.append("\n\t\tUserId: ").append(loginUser.getUserId())
				.append("\n\t\tName: ").append(loginUser.getName())
				.append("\n\t\tLevel: ").append(loginUser.getLevel())
				.append("\n\t\tOnline: ").append(loginUser.getOnline());
		}
		
		return output.toString();
	}
}
