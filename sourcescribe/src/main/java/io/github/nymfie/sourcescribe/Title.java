package io.github.nymfie.sourcescribe;

import java.util.List;
import java.util.Objects;

import org.jspecify.annotations.Nullable;

public final class Title {
	Level level;
	@Nullable
	Id id;
	String text;
	boolean allow_styling;
	
	public Title(Level level, @Nullable Id id, String text, boolean allow_styling) {
		this.level = Objects.requireNonNull(level);
		this.id = id;
		this.text = Objects.requireNonNull(text);
		this.allow_styling = allow_styling;
	}
	
	public Title(Level level, String text, boolean allow_styling) {
		this(level, null, text, allow_styling);
	}
	
	public Title(Title other) {
		copy(other);
	}
	
	public void copy(Title other) {
		level = other.level;
		id = other.id;
		text = other.text;
		allow_styling = other.allow_styling;
	}
	
	/**
	 * Sets the category ID for this group (e.g. error or diagnostic ID)<br>
	 * <br>
	 * Text passed to this function is untrusted. It will be normalized and is not
	 * allowed to be styled.
	 * 
	 * @param value - the new friendly ID
	 * @return this title
	 */
	public Title id(String newId) {
		newId = Objects.requireNonNull(newId);
		if (id == null)
			id = new Id(newId);
		else
			id.id(newId);
		return this;
	}
	
	/**
	 * Sets the accompanying URL for this group's friendly ID<br>
	 * <br>
	 * If the friendly ID is not set, this caches the URL but is irrelevant until an ID is set
	 * 
	 * @param newUrl - the new URL
	 * @return this title
	 */
	public Title id_url(String newUrl) {
		newUrl = Objects.requireNonNull(newUrl);
		if (this.id == null)
			this.id = new Id(null, newUrl);
		else
			this.id.url(newUrl);
		return this;
	}
	
	public @Nullable Id id() {
		return id;
	}
	
	/**
	 * Create a new group with this title, containing the specified element
	 */
	public Group element(Element section) {
		return Group.withTitle(this).element(section);
	}
	
	/**
	 * Create a new group with this title, containing the specified elements
	 */
	public Group elements(List<? extends Element> sections) {
		return Group.withTitle(this).elements(sections);
	}
}
