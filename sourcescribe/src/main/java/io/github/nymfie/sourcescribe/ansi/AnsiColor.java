package io.github.nymfie.sourcescribe.ansi;

public enum AnsiColor implements Color {
	BLACK, RED, GREEN, YELLOW, BLUE, MAGENTA, CYAN, WHITE,

	BRIGHT_BLACK, BRIGHT_RED, BRIGHT_GREEN, BRIGHT_YELLOW, BRIGHT_BLUE, BRIGHT_MAGENTA, BRIGHT_CYAN, BRIGHT_WHITE;

	public AnsiColor bright(boolean yes) {
		return switch (this) {
		case BLACK, BRIGHT_BLACK -> yes ? BRIGHT_BLACK : BLACK;
		case RED, BRIGHT_RED -> yes ? BRIGHT_RED : RED;
		case GREEN, BRIGHT_GREEN -> yes ? BRIGHT_GREEN : GREEN;
		case YELLOW, BRIGHT_YELLOW -> yes ? BRIGHT_YELLOW : YELLOW;
		case BLUE, BRIGHT_BLUE -> yes ? BRIGHT_BLUE : BLUE;
		case MAGENTA, BRIGHT_MAGENTA -> yes ? BRIGHT_MAGENTA : MAGENTA;
		case CYAN, BRIGHT_CYAN -> yes ? BRIGHT_CYAN : CYAN;
		case WHITE, BRIGHT_WHITE -> yes ? BRIGHT_WHITE : WHITE;
		};
	}

	public boolean isBright() {
		return switch (this) {
		case BLACK -> false;
		case RED -> false;
		case GREEN -> false;
		case YELLOW -> false;
		case BLUE -> false;
		case MAGENTA -> false;
		case CYAN -> false;
		case WHITE -> false;
		case BRIGHT_BLACK -> true;
		case BRIGHT_RED -> true;
		case BRIGHT_GREEN -> true;
		case BRIGHT_YELLOW -> true;
		case BRIGHT_BLUE -> true;
		case BRIGHT_MAGENTA -> true;
		case BRIGHT_CYAN -> true;
		case BRIGHT_WHITE -> true;
		};
	}

	/** {@inheritDoc} */
	@Override
	public String asFgParameter() {
		return switch (this) {
		case BLACK -> "30";
		case RED -> "31";
		case GREEN -> "32";
		case YELLOW -> "33";
		case BLUE -> "34";
		case MAGENTA -> "35";
		case CYAN -> "36";
		case WHITE -> "37";
		case BRIGHT_BLACK -> "90";
		case BRIGHT_RED -> "91";
		case BRIGHT_GREEN -> "92";
		case BRIGHT_YELLOW -> "93";
		case BRIGHT_BLUE -> "94";
		case BRIGHT_MAGENTA -> "95";
		case BRIGHT_CYAN-> "96";
		case BRIGHT_WHITE -> "97";
		};
	}

	/** {@inheritDoc} */
	@Override
	public String asBgParameter() {
		return switch (this) {
		case BLACK -> "40";
		case RED -> "41";
		case GREEN -> "42";
		case YELLOW -> "43";
		case BLUE -> "44";
		case CYAN -> "45";
		case MAGENTA -> "46";
		case WHITE -> "47";
		case BRIGHT_BLACK -> "100";
		case BRIGHT_RED -> "101";
		case BRIGHT_GREEN -> "102";
		case BRIGHT_YELLOW -> "103";
		case BRIGHT_BLUE -> "104";
		case BRIGHT_CYAN -> "105";
		case BRIGHT_MAGENTA -> "106";
		case BRIGHT_WHITE -> "107";
		};
	}
}
