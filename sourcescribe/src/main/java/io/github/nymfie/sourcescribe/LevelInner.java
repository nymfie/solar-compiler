package io.github.nymfie.sourcescribe;

import io.github.nymfie.sourcescribe.ansi.Style;
import io.github.nymfie.sourcescribe.render.Stylesheet;

public enum LevelInner {
	ERROR, WARNING, INFO, NOTE, HELP;

	public Style style(Stylesheet stylesheet) {
		return switch (this) {
		case ERROR -> stylesheet.error;
		case WARNING -> stylesheet.warning;
		case INFO -> stylesheet.info;
		case NOTE -> stylesheet.note;
		case HELP -> stylesheet.help;
		};
	}
}
