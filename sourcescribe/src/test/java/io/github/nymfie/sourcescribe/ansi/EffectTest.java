package io.github.nymfie.sourcescribe.ansi;

import static org.junit.jupiter.api.Assertions.*;

import java.util.EnumSet;

import org.junit.jupiter.api.Test;

class EffectTest {

	@Test
	void testNewPlain() {
		assertEquals("", Effect.effectsAsParameters(Effect.newPlain()));
	}

	@Test
	void testEffectsAsSequence() {
		assertEquals("7", Effect.effectsAsParameters(EnumSet.of(Effect.INVERT)));
		// Order should be in the natural enum order when multiple effects are applied
		assertEquals("1;3;21", Effect.effectsAsParameters(EnumSet.of(Effect.BOLD, Effect.DOUBLE_UNDERLINE, Effect.ITALIC)));
		assertNotEquals("9;8", Effect.effectsAsParameters(EnumSet.of(Effect.STRIKETHROUGH, Effect.HIDDEN)));
	}

	@Test
	void testAsSequence() {
		assertEquals("5", Effect.BLINK.asParameter());
	}

}