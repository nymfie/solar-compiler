package io.github.nymfie.sourcescribe;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.jspecify.annotations.Nullable;

sealed interface SnippetMarker permits Annotation, Patch { }

/**
 * Represents a snippet of source code.
 * @see AnnotationSnippet
 * @see PatchSnippet
 */
public abstract sealed class Snippet<T extends Snippet<T, V>, V extends SnippetMarker> permits AnnotationSnippet, PatchSnippet {
	@Nullable String path = null;
	long lineStart = 1;
	final SharedSource source;
	final List<V> markers = new ArrayList<V>();
	boolean fold = true;
	
	Snippet(SharedSource source) {
		this.source = Objects.requireNonNull(source);
	}
	
	public static AnnotationSnippet annotateSource(SharedSource source) {
		return new AnnotationSnippet(source);
	}
	
	public static PatchSnippet patchSource(SharedSource source) {
		return new PatchSnippet(source);
	}
	
	@SuppressWarnings("unchecked")
	public T lineStart(long newLineStart) {
		lineStart = newLineStart;
		return (T) this;
	}
	
	public long lineStart() {
		return lineStart;
	}
	
	@SuppressWarnings("unchecked")
	public T path(@Nullable String newPath) {
		path = newPath;
		return (T) this;
	}
	
	public @Nullable String path() {
		return path;
	}
	
	@SuppressWarnings("unchecked")
	public T fold(boolean newFold) {
		fold = newFold;
		return (T) this;
	}
	
	public boolean fold() {
		return fold;
	}
	
	@SuppressWarnings("unchecked")
	protected T addOne(V marker) {
		markers.add(Objects.requireNonNull(marker));
		return (T) this;
	}
	
	@SuppressWarnings("unchecked")
	protected T addMany(List<? extends V> markers) {
		this.markers.addAll(markers);
		return (T) this;
	}
}
