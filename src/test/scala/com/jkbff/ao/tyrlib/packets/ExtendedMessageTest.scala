package com.jkbff.ao.tyrlib.packets

import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.DataInputStream
import java.io.DataOutputStream
import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner
import java.nio.charset.Charset
import java.nio.charset.StandardCharsets
import com.jkbff.ao.tyrlib.chat.Helper
import com.jkbff.ao.tyrlib.chat.MMDBParser

@RunWith(classOf[JUnitRunner])
class ExtendedMessageTest extends FunSuite {
	test("parseParams") {
		val baos = new ByteArrayOutputStream
		val output = new DataOutputStream(baos)
		
		val value0 = "hi this is a test"
		output.writeByte('S'.asInstanceOf[Int])
		output.writeShort(value0.size)
		output.write(value0.getBytes(StandardCharsets.ISO_8859_1))
		
		val value1 = "hi this is also a test"
		output.writeByte('s'.asInstanceOf[Int])
		output.writeByte(value1.size)
		output.write(value1.getBytes(StandardCharsets.ISO_8859_1))
		
		val value2 = 2232342L
		output.writeByte('I'.asInstanceOf[Int])
		output.writeInt(value2.asInstanceOf[Int])
		
		val value3 = 18838393
		output.writeByte('l'.asInstanceOf[Int])
		output.writeInt(value3.asInstanceOf[Int])
		
		val stream = new DataInputStream(new ByteArrayInputStream(baos.toByteArray))

		val extendedMessageParser = new ExtendedMessageParser(MMDBParser.createInstanceFromClasspath())
		val result = extendedMessageParser.parseParams(stream)
		assert(result.size() == 4)
		assert(result.get(0) == value0)
		assert(result.get(1) == value1)
		assert(result.get(2) == value2)
		assert(result.get(3) == "Removing %d #1{ 1:buddy | buddies }.")
	}
}