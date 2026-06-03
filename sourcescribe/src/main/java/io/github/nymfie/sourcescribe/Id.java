package io.github.nymfie.sourcescribe;

import java.util.Objects;

import org.jspecify.annotations.Nullable;

public final class Id {
	/**
	 * The user-friendly ID for a diagnostic
	 */
	@Nullable
	String id;
	/**
	 * An optional URL for the diagnostic ID
	 */
	@Nullable
	String url;
	
	public Id(@Nullable String id, @Nullable String url) {
		this.id = id;
		this.url = url;
	}
	
	public Id(@Nullable String id) {
		this(id, null);
	}
	
	public Id() {
		this(null, null);
	}
	
	public void id(@Nullable String newId) {
		id = Objects.requireNonNull(newId);
	}
	
	public @Nullable String id() {
		return id;
	}
	
	public void url(@Nullable String newUrl) {
		url = newUrl;
	}
	
	public @Nullable String url() {
		return url;
	}
}