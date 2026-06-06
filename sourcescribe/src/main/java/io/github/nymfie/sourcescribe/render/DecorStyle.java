package io.github.nymfie.sourcescribe.render;

public enum DecorStyle {
	ASCII, UNICODE;
	
	/**
	 * Return the prefix string for the file start line of a diagnostic message
	 * @param style - the decor style to use
	 * @param isFirst - whether this is the first diagnostic message
	 * @param isAlone - whether this is the only diagnostic message (ignored if isFirst is false)
	 */
	public static String fileStart(DecorStyle style, boolean isFirst, boolean isAlone) {
		return switch (style) {
		case DecorStyle.ASCII -> "--> ";
		case DecorStyle.UNICODE -> isFirst && isAlone ? " ─▸ " :
								   isFirst ? " ─▸ " :
								   " ├▸ ";
		};
	}
	
	/**
	 * Return the column separator that separates the line number column from the diagnostic messages
	 * when no other separator types apply
	 * @param style - the decor style to use
	 * @param isContinued - whether the separator line continues below or this is the last one
	 * 
	 */
	public static String colSeparator(DecorStyle style, boolean isContinued) {
		return switch (style) {
		case DecorStyle.ASCII -> "|";
		case DecorStyle.UNICODE -> isContinued ? "│" : "╰─";
		};
	}
	
	/**
	 * Return the prefix string for diagnostic messages
	 * @param style - the decor style to use
	 * @param isContinued - whether the separator line continues below or this is the last one
	 */
	public static String messageSeparator(DecorStyle style, boolean isContinued) {
		return switch(style) {
		case DecorStyle.ASCII -> "= ";
		case DecorStyle.UNICODE -> isContinued ? "├ " : "╰ ";
		};
	}
	
	/**
	 * Return the separator string that appears between multiple suggestions
	 * @param style - the decor style to use
	 */
	public static String multiSuggestionSeparator(DecorStyle style) {
		return switch(style) {
		case DecorStyle.ASCII -> "|";
		case DecorStyle.UNICODE -> "├╴";
		};
	}
}
