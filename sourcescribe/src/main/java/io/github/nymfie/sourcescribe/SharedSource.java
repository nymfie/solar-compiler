package io.github.nymfie.sourcescribe;

import java.util.Objects;

import org.jspecify.annotations.Nullable;

/**
 * Stores shareable source code text to avoid duplication
 */
public record SharedSource(@Nullable String path, String source) {
	public SharedSource {
		source = Objects.requireNonNull(source);
	}
	
	public SharedSource(String source) {
		this(null, source);
	}
}
