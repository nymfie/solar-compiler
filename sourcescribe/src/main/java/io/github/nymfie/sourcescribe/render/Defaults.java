package io.github.nymfie.sourcescribe.render;

import io.github.nymfie.sourcescribe.ansi.AnsiColor;
import io.github.nymfie.sourcescribe.ansi.Style;

public final class Defaults {
	private Defaults() { }
	
	public final static boolean USE_WINDOWS_COLORS = System.getProperty("os.name").startsWith("Windows");
	
	public final static String ANONYMIZED_LINE_NUM = "LL";
	
	public final static long DEFAULT_TERM_WIDTH = 120;
	
	
	// Default styles
	private final static AnsiColor BRIGHT_BLUE = USE_WINDOWS_COLORS ? AnsiColor.BRIGHT_CYAN : AnsiColor.BRIGHT_BLUE;
	public final static Stylesheet DEFAULT_STYLESHEET = new Stylesheet(
			/* error    */ AnsiColor.BRIGHT_RED.onDefault().bold(true),
			/* warning  */ AnsiColor.YELLOW.bright(USE_WINDOWS_COLORS).onDefault().bold(true),
			/* info     */ BRIGHT_BLUE.onDefault().bold(true),
			/* note     */ AnsiColor.BRIGHT_GREEN.onDefault().bold(true),
			/* help     */ AnsiColor.BRIGHT_CYAN.onDefault().bold(true),
			/* lineNum  */ BRIGHT_BLUE.onDefault().bold(true),
			/* emphasis */ (USE_WINDOWS_COLORS ? AnsiColor.BRIGHT_WHITE.onDefault() : new Style()).bold(true),
			/* none     */ new Style(),
			/* context  */ BRIGHT_BLUE.onDefault().bold(true),
			/* addition */ AnsiColor.BRIGHT_GREEN.onDefault(),
			/* removal  */ AnsiColor.BRIGHT_RED.onDefault()
	);
							
}
