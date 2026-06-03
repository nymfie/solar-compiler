package io.github.nymfie.sourcescribe;

import java.util.List;

// Implementors
public final class AnnotationSnippet extends Snippet<AnnotationSnippet, Annotation> {
	public AnnotationSnippet(SharedSource source) {
		super(source);
	}
	
	public AnnotationSnippet annotate(Annotation annotation) {
		return addOne(annotation);
	}
	
	public AnnotationSnippet annotates(List<? extends Annotation> annotations) {
		return addMany(annotations);
	}
}