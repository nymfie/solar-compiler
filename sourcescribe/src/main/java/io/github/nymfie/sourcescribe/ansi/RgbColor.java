package io.github.nymfie.sourcescribe.ansi;

public record RgbColor(byte r, byte g, byte b) implements Color {
	/** {@inheritDoc} */
	@Override
	public String asFgParameter() {
		return "38;2;" + Integer.toUnsignedString(Byte.toUnsignedInt(r)) + ";"
				       + Integer.toUnsignedString(Byte.toUnsignedInt(g)) + ";"
				       + Integer.toUnsignedString(Byte.toUnsignedInt(b));
	}

	/** {@inheritDoc} */
	@Override
	public String asBgParameter() {
		return "48;2;" + Integer.toUnsignedString(Byte.toUnsignedInt(r)) + ";"
			           + Integer.toUnsignedString(Byte.toUnsignedInt(g)) + ";"
			           + Integer.toUnsignedString(Byte.toUnsignedInt(b));
	}
}
