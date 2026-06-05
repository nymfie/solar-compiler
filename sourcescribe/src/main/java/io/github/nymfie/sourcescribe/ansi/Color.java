package io.github.nymfie.sourcescribe.ansi;

public sealed interface Color permits AnsiColor, Ansi256Color, RgbColor {
	default public Style on(Color background) {
		return new Style(this, background);
	}
	
	default public Style onDefault() {
		return new Style(this, null);
	}
	
	/**
	 * Returns the ANSI escape sequence parameters for this colour as foreground<br>
	 * Note: this does not return the escape sequence or 'm' SGR command
	 */
	public String asFgParameter();
	
	/**
	 * Returns the ANSI escape sequence parameters for this colour as background<br>
	 * Note: this does not return the escape sequence or 'm' SGR command
	 */
	public String asBgParameter();
}