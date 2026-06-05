package io.github.nymfie.sourcescribe.ansi;

import java.util.Objects;

import org.jspecify.annotations.Nullable;

public record Ansi256Color(byte color) implements Color {	
	public static Ansi256Color fromAnsiColor(AnsiColor color) {
		return switch (Objects.requireNonNull(color)) {
		case BLACK -> new Ansi256Color((byte) 0);
		case RED -> new Ansi256Color((byte) 1);
		case GREEN -> new Ansi256Color((byte) 2);
		case YELLOW -> new Ansi256Color((byte) 3);
		case BLUE -> new Ansi256Color((byte) 4);
		case MAGENTA -> new Ansi256Color((byte) 5);
		case CYAN -> new Ansi256Color((byte) 6);
		case WHITE -> new Ansi256Color((byte) 7);
		case BRIGHT_BLACK -> new Ansi256Color((byte) 8);
		case BRIGHT_RED -> new Ansi256Color((byte) 9);
		case BRIGHT_GREEN -> new Ansi256Color((byte) 10);
		case BRIGHT_YELLOW -> new Ansi256Color((byte) 11);
		case BRIGHT_BLUE -> new Ansi256Color((byte) 12);
		case BRIGHT_MAGENTA -> new Ansi256Color((byte) 13);
		case BRIGHT_CYAN -> new Ansi256Color((byte) 14);
		case BRIGHT_WHITE -> new Ansi256Color((byte) 15);
		};
	}

	public @Nullable AnsiColor tryToAnsiColor() {
		return switch (color) {
		case 0 -> AnsiColor.BLACK;
		case 1 -> AnsiColor.RED;
		case 2 -> AnsiColor.GREEN;
		case 3 -> AnsiColor.YELLOW;
		case 4 -> AnsiColor.BLUE;
		case 5 -> AnsiColor.MAGENTA;
		case 6 -> AnsiColor.CYAN;
		case 7 -> AnsiColor.WHITE;
		case 8 -> AnsiColor.BRIGHT_BLACK;
		case 9 -> AnsiColor.BRIGHT_RED;
		case 10 -> AnsiColor.BRIGHT_GREEN;
		case 11 -> AnsiColor.BRIGHT_YELLOW;
		case 12 -> AnsiColor.BRIGHT_BLUE;
		case 13 -> AnsiColor.BRIGHT_MAGENTA;
		case 14 -> AnsiColor.BRIGHT_CYAN;
		case 15 -> AnsiColor.BRIGHT_WHITE;
		default -> null;
		};
	}

	/** {@inheritDoc} */
	@Override
	public String asFgParameter() {
		return "38;5;" + Integer.toUnsignedString(Byte.toUnsignedInt(color));
	}

	/** {@inheritDoc} */
	@Override
	public String asBgParameter() {
		return "48;5;" + Integer.toUnsignedString(Byte.toUnsignedInt(color));
	}
}
