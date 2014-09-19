package com.jkbff.ao.tyrlib.chat

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class MMDBParserTest extends FunSuite {
	test("test") {
		println(MMDBParser.getMessage(20000L, 264474563L))
	}
}