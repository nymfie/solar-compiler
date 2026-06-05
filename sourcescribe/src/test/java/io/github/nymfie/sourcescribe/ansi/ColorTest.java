package io.github.nymfie.sourcescribe.ansi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ColorTest {
	@Test
	void testStyle() {
		assertNull(AnsiColor.MAGENTA.onDefault().bgColor()); // Default colour is "null"
		assertEquals(AnsiColor.WHITE, AnsiColor.WHITE.onDefault().fgColor());
		assertEquals(AnsiColor.BRIGHT_RED, AnsiColor.BLUE.on(AnsiColor.RED.bright(true)).bgColor());
	}

	@Test
	void testAnsiColorBright() {		
		assertEquals(AnsiColor.BRIGHT_BLACK, AnsiColor.BLACK.bright(true));
		assertEquals(AnsiColor.MAGENTA, AnsiColor.MAGENTA.bright(false));
		assertEquals(AnsiColor.BRIGHT_RED, AnsiColor.RED.bright(true));
		assertEquals(AnsiColor.WHITE, AnsiColor.BRIGHT_WHITE.bright(false));
		
		assertTrue(AnsiColor.BRIGHT_CYAN.isBright());
		assertFalse(AnsiColor.GREEN.isBright());
		
	}
	
	@Test
	void testAnsiColorParameter() {
		assertEquals("30", AnsiColor.BLACK.asFgParameter());
		assertEquals("47", AnsiColor.WHITE.asBgParameter());
		assertEquals("95", AnsiColor.BRIGHT_MAGENTA.asFgParameter());
		assertEquals("104", AnsiColor.BRIGHT_BLUE.asBgParameter());
		
		assertEquals(AnsiColor.BRIGHT_GREEN.asFgParameter(), AnsiColor.GREEN.bright(true).asFgParameter());
	}
	
	@Test
	void testAnsi256ColorConversion() {
		assertEquals((byte)10, Ansi256Color.fromAnsiColor(AnsiColor.BRIGHT_GREEN).color());
		assertEquals(AnsiColor.MAGENTA, (new Ansi256Color((byte)5)).tryToAnsiColor());
		assertNull((new Ansi256Color((byte)220)).tryToAnsiColor());
	}
	
	@Test
	void testAnsi256ColorParameters() {
		assertEquals("38;5;232", (new Ansi256Color((byte)232).asFgParameter()));
		assertEquals("48;5;255", (new Ansi256Color((byte)255).asBgParameter()));
	}
	
	@Test
	void testRgbColorParameters() {
		assertEquals("38;2;10;20;30", (new RgbColor((byte)10, (byte)20, (byte)30)).asFgParameter());
		assertEquals("48;2;30;20;10", (new RgbColor((byte)30, (byte)20, (byte)10)).asBgParameter());
	}

}
