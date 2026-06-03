package io.github.nymfie.sourcescribe;

import java.util.Objects;

import org.jspecify.annotations.Nullable;

public final class Origin {
	final String path;
	@Nullable Long line = null;
	@Nullable Long charColumn = null;
	
	public Origin(String path) {
		this.path = Objects.requireNonNull(path);
	}
	
	public static Origin path(String path) {
		return new Origin(path);
	}
	
	public Origin line(@Nullable Long newLine) {
		line = newLine;
		return this;
	}
	
	public @Nullable Long line() {
		return line;
	}
	
	public Origin charColumn(@Nullable Long newCharColumn) {
		charColumn = newCharColumn;
		return this;
	}
	
	public @Nullable Long charColumn() {
		return charColumn;
	}
}
