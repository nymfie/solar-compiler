package io.github.nymfie.sourcescribe;

import java.util.Objects;

import org.jspecify.annotations.Nullable;

import io.github.nymfie.sourcescribe.ansi.Style;
import io.github.nymfie.sourcescribe.render.Stylesheet;

public record Level(@Nullable String name, LevelInner level) {
	public final static Level ERROR = new Level(LevelInner.ERROR);
	public final static Level WARNING = new Level(LevelInner.WARNING);
	public final static Level INFO = new Level(LevelInner.INFO);
	public final static Level NOTE = new Level(LevelInner.NOTE);
	public final static Level HELP = new Level(LevelInner.HELP);

	public final static String ERROR_TXT = "error";
	public final static String WARNING_TXT = "warning";
	public final static String INFO_TXT = "info";
	public final static String NOTE_TXT = "note";
	public final static String HELP_TXT = "help";

	public Level {
		level = Objects.requireNonNull(level);
	}

	public Level(LevelInner level) {
		this(null, level);
	}

	/**
	 * Create title of this level for the primary (first) group cause.<br>
	 * <br>
	 * Text passed to this function is untrusted. It will be normalized and is not
	 * allowed to be styled.
	 * 
	 * @param title - the string title
	 * @return - a new Title with this level and the specified title
	 * @see Group#withTitle(Title)
	 * @see Level#secondaryTitle(String)
	 */
	public Title primaryTitle(String title) {
		return new Title(this, title, false);
	}

	/**
	 * Create title of this level for the primary (first) group cause.<br>
	 * <br>
	 * Text passed to this function is trusted. It will not be normalized and is
	 * allowed to be styled.
	 * 
	 * @param title - the string title
	 * @return - a new Title with this level and the specified title
	 * @see Group#withTitle(Title)
	 */
	// TODO: Add normalization function in renderer
	public Title secondaryTitle(String title) {
		return new Title(this, title, true);
	}
	
	/**
	 * Create a text message with this level to be inserted within a group<br>
	 * <br>
	 * Text passed to this function is trusted. It will not be normalized and is
	 * allowed to be styled.
	 * 
	 * @param text - the text message
	 * @return - a new Message with this level
	 */
	public Message message(String text) {
		return new Message(this, text);
	}

	/**
	 * @param name - the name of the level to create
	 * @return a new Level with the specified name and same level type as this
	 *         instance
	 */
	public Level withName(String name) {
		return new Level(name, level);
	}

	/**
	 * @return a new Level with the same level type and no name
	 */
	public Level noName() {
		return new Level(level);
	}

	public Style style(Stylesheet stylesheet) {
		return level.style(stylesheet);
	}

	@Override
	public String toString() {
		if (name != null)
			return name;

		return switch (level) {
		case ERROR -> ERROR_TXT;
		case WARNING -> WARNING_TXT;
		case INFO -> INFO_TXT;
		case NOTE -> NOTE_TXT;
		case HELP -> HELP_TXT;
		};
	}
}
