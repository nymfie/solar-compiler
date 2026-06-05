package io.github.nymfie.sourcescribe.ansi;

import java.util.Collections;
import java.util.EnumSet;

import org.jspecify.annotations.Nullable;

public final class Style {
	private @Nullable Color fg = null;
	private @Nullable Color bg = null;
	/* underline colouring is not implemented */
	private EnumSet<Effect> effects = Effect.newPlain();

	/**
	 * The ANSI sequence for resetting all formatting
	 */
	public final static String RESET = "\u001b[0m";
	
	public Style(@Nullable Color fg, @Nullable Color bg, Effect... effects) {
		this.fg = fg;
		this.bg = bg;
		this.effects = Effect.newPlain();
		Collections.addAll(this.effects, effects);
	}
	
	public Style(Effect... effects) {
		this(null, null, effects);
	}
	
	public Style(Style other) {
		copy(other);
	}
	
	public void copy(Style other) {
		fg = other.fg;
		bg = other.bg;
		effects = other.effects();
	}

	/**
	 * Set a new foreground colour for this style
	 */
	public Style fgColor(@Nullable Color newFg) {
		fg = newFg;
		return this;
	}
	/**
	 * Get the foreground colour of this style
	 */
	public @Nullable Color fgColor() {
		return fg;
	}

	/**
	 * Set a new background colour for this style
	 */
	public Style bgColor(@Nullable Color newBg) {
		bg = newBg;
		return this;
	}
	/**
	 * Get the background colour of this style
	 * @return
	 */
	public @Nullable Color bgColor() {
		return bg;
	}

	/**
	 * Set a new set of effects for this style, erasing the previous for this style.<br>
	 * The new effects are cloned from the parameter instead of being shared.
	 */
	public Style effects(EnumSet<Effect> newEffects) {
		effects = newEffects.clone();
		return this;
	}
	/**
	 * Add a set of effects to this style's pre-existing effects
	 */
	public Style addEffects(EnumSet<Effect> newEffects) {
		effects.addAll(newEffects);
		return this;
	}
	/**
	 * Remove a set of effects from this style's pre-existing effects
	 */
	public Style removeEffects(EnumSet<Effect> removedEffects) {
		effects.removeAll(removedEffects);
		return this;
	}
	/**
	 * Get a copy of the effects of this style.
	 */
	public EnumSet<Effect> effects() {
		return effects.clone();
	}
	
	/**
	 * Return whether the Style is plain or not (no styling information applied)
	 */
	public boolean isPlain() {
		return (fg == null) && (bg == null) && (effects.isEmpty());
	}

	/**
	 * Returns the ANSI command applying this style, including the escape sequence
	 * and the SGR "m" parameter
	 */
	public String asANSISequence() {
		// If empty, return empty string
		if (isPlain())
			return "";

		// Otherwise create the full sequence
		String result = Effect.effectsAsParameters(effects);
		
		if (fg != null)
			result += (result.length() > 0 ? ";" : "") + fg.asFgParameter();
		if (bg != null)
			result += (result.length() > 0 ? ";" : "") + bg.asBgParameter();

		return "\u001b[" + result + "m";
	}

	/* CONVENIENCE METHODS */
	public Style effect(Effect effect, boolean set) {
		if (set)
			effects.add(effect);
		else
			effects.remove(effect);
		return this;
	}
	public boolean isEffect(Effect effect) {
		return effects.contains(effect);
	}
	
	public Style bold(boolean set) { return effect(Effect.BOLD, set); }
	public boolean isBold() { return isEffect(Effect.BOLD); }
	
	public Style dimmed(boolean set) { return effect(Effect.DIMMED, set); }
	public boolean isDimmed() { return isEffect(Effect.DIMMED); }
	
	public Style italic(boolean set) { return effect(Effect.ITALIC, set); }
	public boolean isItalic() { return isEffect(Effect.ITALIC); }
	
	public Style underline(boolean set) { return effect(Effect.UNDERLINE, set); }
	public boolean isUnderline() { return isEffect(Effect.UNDERLINE); }
	
	public Style blink(boolean set) { return effect(Effect.BLINK, set); }
	public boolean isBlink() { return isEffect(Effect.BLINK); }
	
	public Style invert(boolean set) { return effect(Effect.INVERT, set); }
	public boolean isInvert() { return isEffect(Effect.INVERT); }
	
	public Style hidden(boolean set) { return effect(Effect.HIDDEN, set); }
	public boolean isHidden() { return isEffect(Effect.HIDDEN); }
	
	public Style strikethrough(boolean set) { return effect(Effect.STRIKETHROUGH, set); }
	public boolean isStrikethrough() { return isEffect(Effect.STRIKETHROUGH); }
	
	public Style doubleUnderline(boolean set) { return effect(Effect.DOUBLE_UNDERLINE, set); }
	public boolean isDoubleUnderline() { return isEffect(Effect.DOUBLE_UNDERLINE); }
}