package io.github.nymfie.sourcescribe;

import java.util.Objects;

import org.jspecify.annotations.Nullable;

/**
 * Add a remark to a piece of code
 */
public final class Annotation implements SnippetMarker {
	ByteSpan span;
	/** Nullable string */
	@Nullable
	String reason;
	AnnotationKind kind;
	boolean highlightSource;

	/**
	 * Create a new annotation.
	 * 
	 * @param span            - the span of bytes covered by this annotation
	 * @param reason          - the string describing the reason for this annotation
	 * @param kind            - the kind of annotation, dictating the styling
	 *                        applied
	 * @param highlightSource - whether to give extra emphasis to the annotation or
	 *                        not
	 */
	public Annotation(ByteSpan span, @Nullable String reason, AnnotationKind kind, boolean highlightSource) {
		this.span = Objects.requireNonNull(span);
		this.reason = reason;
		this.kind = Objects.requireNonNull(kind);
		this.highlightSource = highlightSource;
	}

	/**
	 * Creates a new annotation with no specified reason
	 * 
	 * @param span            - the span of bytes covered by this annotation
	 * @param kind            - the kind of annotation, dictating the styling
	 *                        applied
	 * @param highlightSource - whether to give extra emphasis to the annotation or
	 *                        not
	 */
	public Annotation(ByteSpan span, AnnotationKind kind, boolean highlightSource) {
		this(span, null, kind, highlightSource);
	}

	public Annotation(Annotation other) {
		copy(other);
	}

	public void copy(Annotation other) {
		span = other.span;
		reason = other.reason;
		kind = other.kind;
		highlightSource = other.highlightSource;
	}
	
	public ByteSpan span() {
		return span;
	}

	/**
	 * @param newReason - the new reason for this annotation, or null to remove the
	 *                  current reason
	 * @return this annotation
	 */
	public Annotation reason(@Nullable String newReason) {
		reason = newReason;
		return this;
	}
	
	public @Nullable String reason() {
		return reason;
	}
	
	public AnnotationKind kind() {
		return kind;
	}

	/**
	 * @param newHighlightSource - whether to give extra emphasis to the annotation
	 *                           or not
	 * @return this annotation
	 */
	public Annotation highlightSource(boolean newHighlightSource) {
		highlightSource = newHighlightSource;
		return this;
	}
	
	public boolean highlightSource() {
		return highlightSource;
	}
}