package io.github.nymfie.sourcescribe.ansi;

import java.util.EnumSet;

public enum Effect {
	BOLD("1"),
	DIMMED("2"),
	ITALIC("3"),
	UNDERLINE("4"),
	BLINK("5"),
	INVERT("7"),
	HIDDEN("8"),
	STRIKETHROUGH("9"),
	DOUBLE_UNDERLINE("21");
	
	final private String parameter;
	
	private Effect(String parameter) {
		this.parameter = parameter;
	}
	
	public static EnumSet<Effect> newPlain() {
		return EnumSet.noneOf(Effect.class);
	}
	
	/**
	 * Returns the ANSI escape sequence parameters for a set of effects<br>
	 * Note: this does not return the escape sequence or 'm' SGR command
	 * @param effects
	 * @return
	 */
	public static String effectsAsParameters(EnumSet<Effect> effects) {
		String result = "";
		for (Effect effect : effects) {
			if (result.length() > 0)
				result += ";";
			result += effect.parameter;
		}
		return result;
	}
	
	/**
	 * Returns the ANSI escape sequence parameter for this effect<br>
	 * Note: this does not return the escape sequence or 'm' SGR command
	 */
	public String asParameter() {
		return parameter;
	}
	
}
