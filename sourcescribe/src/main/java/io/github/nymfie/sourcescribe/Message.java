package io.github.nymfie.sourcescribe;

import java.util.Objects;

public record Message(Level level, String text) {
	public Message {
		level = Objects.requireNonNull(level);
		text = Objects.requireNonNull(text);
	}
}
