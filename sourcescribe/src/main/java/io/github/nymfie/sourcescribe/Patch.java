package io.github.nymfie.sourcescribe;

import java.util.Objects;

/**
 * Suggest a replacement within a snippet
 */
public record Patch(ByteSpan span, String replacement) implements SnippetMarker {
	public Patch {
		span = Objects.requireNonNull(span);
		replacement = Objects.requireNonNull(replacement);
	}
}