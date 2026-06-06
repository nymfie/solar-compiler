package io.github.nymfie.sourcescribe.render;

import java.util.Objects;

import org.jspecify.annotations.Nullable;

import io.github.nymfie.sourcescribe.LevelInner;
import io.github.nymfie.sourcescribe.ansi.Style;

sealed interface ElementStyle {
	final static MainHeaderMsg MAIN_HEADER_MSG 			= new MainHeaderMsg();
	final static HeaderMsg HEADER_MSG 					= new HeaderMsg();
	final static LineAndColumn LINE_AND_COLUMN 			= new LineAndColumn();
	final static LineNumber LINE_NUMBER 				= new LineNumber();
	final static Quotation QUOTATION 					= new Quotation();
	final static UnderlinePrimary UNDERLINE_PRIMARY 	= new UnderlinePrimary();
	final static UnderlineSecondary UNDERLINE_SECONDARY = new UnderlineSecondary();
	final static LabelPrimary LABEL_PRIMARY 			= new LabelPrimary();
	final static LabelSecondary LABEL_SECONDARY 		= new LabelSecondary();
	final static NoStyle NO_STYLE 						= new NoStyle();
	final static Addition ADDITION 						= new Addition();
	final static Removal REMOVAL 						= new Removal();
	
	static record MainHeaderMsg() implements ElementStyle { }
	static record HeaderMsg() implements ElementStyle { }
	static record LineAndColumn() implements ElementStyle { }
	static record LineNumber() implements ElementStyle { }
	static record Quotation() implements ElementStyle { }
	static record UnderlinePrimary() implements ElementStyle { }
	static record UnderlineSecondary() implements ElementStyle { }
	static record LabelPrimary() implements ElementStyle { }
	static record LabelSecondary() implements ElementStyle { }
	static record NoStyle() implements ElementStyle { }
	static record Level(LevelInner level) implements ElementStyle { public Level { level = Objects.requireNonNull(level); } }
	static record Addition() implements ElementStyle { }
	static record Removal() implements ElementStyle { }
	
	/**
	 * Get the ANSI style associated with this particular element style
	 * @param level - the level to use for styling this element<br>(only required for {@link ElementStyle.UnderlinePrimary} and {@link ElementStyle.LabelPrimary}, may be null otherwise)
	 * @param stylesheet - the stylesheet to use
	 * @return an ANSI style object
	 * @see ElementStyle#getStyle(Stylesheet)
	 */
	default Style getStyle(io.github.nymfie.sourcescribe.@Nullable Level level, Stylesheet stylesheet) {
		return switch (this) {
		case Addition x -> stylesheet.addition;
		case Removal x -> stylesheet.removal;
		case LineAndColumn x -> stylesheet.none;
		case LineNumber x -> stylesheet.lineNum;
		case Quotation x -> stylesheet.none;
		case MainHeaderMsg x -> stylesheet.emphasis;
		case UnderlinePrimary x -> Objects.requireNonNull(level).style(stylesheet);
		case LabelPrimary x -> Objects.requireNonNull(level).style(stylesheet);
		case UnderlineSecondary x -> stylesheet.context;
		case LabelSecondary x -> stylesheet.context;
		case HeaderMsg x -> stylesheet.none;
		case NoStyle x -> stylesheet.none;
		case Level(LevelInner lvl) -> lvl.style(stylesheet);
		};
	}
	
	/**
	 * Get the ANSI style associated with this particular element style.<br>
	 * If this is {@link ElementStyle.UnderlinePrimary} or {@link ElementStyle.LabelPrimary}, this will fail as you must specify a Level object to retrieve styling information.
	 * 
	 * @param stylesheet - the stylesheet to use
	 * @return an ANSI style object
	 * @see ElementStyle#getStyle(io.github.nymfie.sourcescribe.Level, Stylesheet)
	 */
	default Style getStyle(Stylesheet stylesheet) {
		return getStyle(null, stylesheet);
	}
}