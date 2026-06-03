package io.github.nymfie.sourcescribe;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.jspecify.annotations.Nullable;

/**
 * A Title with supporting Element (section) objects for each self-contained diagnostic message
 */
public final class Group {
	Level primaryLevel;
	@Nullable Title title;
	final List<Element> elements = new ArrayList<Element>();
	
	private Group(Level primaryLevel, @Nullable Title title) {
		this.primaryLevel = Objects.requireNonNull(primaryLevel);
		this.title = title;
	}
	
	/**
	 * Create a group with a title and its level
	 * @param title - the title to use
	 * @return
	 */
	public static Group withTitle(Title title) {
		title = new Title(title);
		return new Group(title.level, title);
	}
	
	/**
	 * Create an untitled group with a specified level
	 * @param level
	 * @return
	 */
	public static Group withLevel(Level level) {
		return new Group(Objects.requireNonNull(level), null);
	}
	
	
	public Group element(Element section) {
		elements.add(section);
		return this;
	}
	
	public Group elements(List<? extends Element> sections) {
		elements.addAll(elements);
		return this;
	}
}