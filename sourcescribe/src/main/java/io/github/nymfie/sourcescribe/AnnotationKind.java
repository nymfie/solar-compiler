package io.github.nymfie.sourcescribe;

public enum AnnotationKind {
	/** Denotes the primary annotation of a group */
	PRIMARY,
	/** Denotes that the annotation is secondary and adds context for the primary annotation */
	CONTEXT,
	/** Denotes that a line is relevant and should be visible, but that it doesn't have any specific markings otherwise. */
	VISIBLE;

	public Annotation spanOver(ByteSpan span) {
		return new Annotation(span, null, this, false);
	}

	public boolean isPrimary() {
		return this == AnnotationKind.PRIMARY;
	}
}
