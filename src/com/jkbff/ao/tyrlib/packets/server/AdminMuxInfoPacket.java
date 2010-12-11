package com.jkbff.ao.tyrlib.packets.server;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import sk.sigp.aobot.client.types.Int;

import com.jkbff.ao.tyrlib.packets.BaseServerPacket;

public class AdminMuxInfoPacket extends BaseServerPacket {

	public static final int TYPE = 1100;
	public static final String NAME = "ADM_MUX_INFO";
	
	private Int[] unknownInt1;
	private Int[] unknownInt2;
	private Int[] unknownInt3;

	public AdminMuxInfoPacket(DataInputStream input) throws IOException {
		
		int unknownInt1Size = input.readUnsignedShort();
		for(int i = 0; i < unknownInt1Size; i++) {
			this.unknownInt1[i] = new Int(input);
		}
		
		int unknownInt2Size = input.readUnsignedShort();
		for(int i = 0; i < unknownInt2Size; i++) {
			this.unknownInt2[i] = new Int(input);
		}
		
		int unknownInt3Size = input.readUnsignedShort();
		for(int i = 0; i < unknownInt3Size; i++) {
			this.unknownInt3[i] = new Int(input);
		}

	}
	
	public AdminMuxInfoPacket(int[] unknownInt1, int[] unknownInt2, int[] unknownInt3) {
		
		for(int i = 0; i < unknownInt1.length; i++) {
			this.unknownInt1[i] = new Int(unknownInt1[i]);
		}
		
		for(int i = 0; i < unknownInt2.length; i++) {
			this.unknownInt2[i] = new Int(unknownInt2[i]);
		}
		
		for(int i = 0; i < unknownInt3.length; i++) {
			this.unknownInt3[i] = new Int(unknownInt3[i]);
		}
		
	}
	
	public int getPacketType() {
		return LoginCharacterListPacket.TYPE;
	}
	
	public byte[] getBytes() throws IOException {
		
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		DataOutputStream outputStream = new DataOutputStream(byteStream);
		
		// write packet type
		outputStream.writeShort(getPacketType());
		
		// write packet payload length
		// +6 accounts for the 2 bytes that indicate the size of each array
		int length = unknownInt1.length + unknownInt2.length + unknownInt3.length + 6;
		outputStream.writeShort(length);
		
		// write payload
		
		// write array length
		outputStream.writeShort(unknownInt1.length);
		for (int i = 0; i < unknownInt1.length; i++) {
			outputStream.write(unknownInt1[i].getRaw());
		}
		
		// write array length
		outputStream.writeShort(unknownInt2.length);
		for (int i = 0; i < unknownInt2.length; i++) {
			outputStream.write(unknownInt2[i].getRaw());
		}
		
		// write array length
		outputStream.writeShort(unknownInt2.length);
		for (int i = 0; i < unknownInt3.length; i++) {
			outputStream.write(unknownInt3[i].getRaw());
		}
		
		return byteStream.toByteArray();
	}
}
