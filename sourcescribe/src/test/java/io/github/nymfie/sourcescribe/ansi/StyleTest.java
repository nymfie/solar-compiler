package io.github.nymfie.sourcescribe.ansi;

import static org.junit.jupiter.api.Assertions.*;

import java.util.EnumSet;

import org.junit.jupiter.api.Test;

class StyleTest {

	@Test
	void testFgColor() {
		assertEquals(new Ansi256Color((byte)137),
					 (new Style(new Ansi256Color((byte)137), null)).fgColor());
	}

	@Test
	void testBgColor() {
		assertEquals(new RgbColor((byte)50, (byte)70, (byte)90),
					 (new Style(null, new RgbColor((byte)50, (byte)70, (byte)90))).bgColor());
	}

	@Test
	void testEffects() {
		Style style1 = new Style(Effect.BOLD, Effect.BLINK);
		Style style2 = new Style();
		
		style2.effects(style1.effects()); // Create a clone of style1, should be independent
		assertEquals(style1.effects(), style2.effects());
		
		// Test independence and 'addEffects'
		style2.addEffects(EnumSet.of(Effect.INVERT));
		assertNotEquals(style1.effects(), style2.effects());
		
		// Test 'removeEffects'
		style2.removeEffects(EnumSet.of(Effect.INVERT));
		assertEquals(style1.effects(), style2.effects());
		
		// Test entire removal, including effects not in the effects set
		style1 = new Style();
		style2.removeEffects(EnumSet.allOf(Effect.class));
		assertEquals(style1.effects(), style2.effects());
	}

	@Test
	void testIsPlain() {
		assertTrue((new Style()).isPlain());
		assertFalse((new Style(Effect.BOLD)).isPlain());
	}

	@Test
	void testAsANSISequence() {
		// RESET should always be the reset code
		assertEquals("\u001b[0m", Style.RESET);
		
		Style style = new Style();
		
		// Empty style should return empty string
		assertEquals("", style.asANSISequence());
		
		// Test with just effects
		style.bold(true).underline(true);
		assertEquals("\u001b[1;4m", style.asANSISequence());
		
		// Test with foreground
		style.fgColor(AnsiColor.MAGENTA);
		assertEquals("\u001b[1;4;35m", style.asANSISequence());
		
		// Test with background
		style.bgColor(AnsiColor.BRIGHT_YELLOW);
		assertEquals("\u001b[1;4;35;103m", style.asANSISequence());
		
		// Try just effects and background
		style.fgColor(null);
		assertEquals("\u001b[1;4;103m", style.asANSISequence());
		
		// Try background with no effects
		style.removeEffects(style.effects());
		assertEquals("\u001b[103m", style.asANSISequence());
		
		// Try readding a foreground with a different pattern
		style.fgColor(new Ansi256Color((byte)255));
		assertEquals("\u001b[38;5;255;103m", style.asANSISequence());
		
		// Try replacing the background with an RGB colour
		style.bgColor(new RgbColor((byte)100, (byte)150, (byte)200));
		assertEquals("\u001b[38;5;255;48;2;100;150;200m", style.asANSISequence());
	}

	@Test
	void testConvenienceEffectMethods() {
		Style style = new Style();
		
		// Try empty
		assertFalse(style.isItalic());
		assertFalse(style.effects().contains(Effect.ITALIC));
		
		// Try setting italic and checking
		style.italic(true);
		assertTrue(style.isItalic());
		assertTrue(style.effects().contains(Effect.ITALIC));
		
		// Check that adding a new effect doesn't reset the previous ones
		style.bold(true);
		assertTrue(style.isItalic());
		assertTrue(style.effects().contains(Effect.ITALIC));
		
		// Try resetting and checking that only italic was removed
		style.italic(false);
		assertFalse(style.isItalic());
		assertFalse(style.effects().contains(Effect.ITALIC));
		assertTrue(style.isBold());
		assertTrue(style.effects().contains(Effect.BOLD));
		
	}

}
