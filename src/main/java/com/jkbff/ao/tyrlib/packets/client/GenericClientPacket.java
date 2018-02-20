package com.jkbff.ao.tyrlib.packets.client;

import org.apache.commons.codec.binary.Hex;
import sk.sigp.aobot.client.types.AbstractType;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class GenericClientPacket extends BaseClientPacket {
	protected final int packetType;
	protected final byte[] data;
	
	public GenericClientPacket(int packetType, int size, DataInputStream input) {
		this.packetType = packetType;
		this.data = new byte[size];
		try {
			input.readFully(this.data);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public GenericClientPacket(int packetType, byte[] data) {
		this.packetType = packetType;
		this.data = data;
	}

	@Override
	public int getPacketType() {
		return this.packetType;
	}

	@Override
	public byte[] getBytes() throws IOException {
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		DataOutputStream outputStream = new DataOutputStream(byteStream);

		outputStream.writeShort(packetType);
		outputStream.writeShort(data.length);
		outputStream.write(data);

		return byteStream.toByteArray();
	}

	@Override
	public AbstractType[] getParameters() {
		return new AbstractType[]{};
	}

	@Override
	public String getTypesAsString() {
		return new String(Hex.encodeHex(data));
	}
}
