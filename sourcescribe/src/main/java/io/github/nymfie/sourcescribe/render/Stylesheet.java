package io.github.nymfie.sourcescribe.render;

import io.github.nymfie.sourcescribe.ansi.Style;

public final class Stylesheet {
	public Style error;
	public Style warning;
	public Style info;
	public Style note;
	public Style help;
	public Style lineNum;
	public Style emphasis;
	public Style none;
	public Style context;
	public Style addition;
	public Style removal;

	public Stylesheet(Style error, Style warning, Style info, Style note, Style help, Style lineNum, Style emphasis, Style none, Style context, Style addition, Style removal) {
		this.error = new Style(error);
		this.warning = new Style(warning);
		this.info = new Style(info);
		this.note = new Style(note);
		this.help = new Style(help);
		this.lineNum = new Style(lineNum);
		this.emphasis = new Style(emphasis);
		this.none = new Style(none);
		this.context = new Style(context);
		this.addition = new Style(addition);
		this.removal = new Style(removal);
	}

	public Stylesheet() {
		this(new Style(), // error
			 new Style(), // warning
			 new Style(), // info
			 new Style(), // note
			 new Style(), // help
			 new Style(), // lineNum
			 new Style(), // emphasis
			 new Style(), // none
			 new Style(), // context
			 new Style(), // addition
			 new Style()); // removal
	}
	
	public Stylesheet(Stylesheet other) {
		this(other.error,
			 other.warning,
			 other.info,
			 other.note,
			 other.help,
			 other.lineNum,
			 other.emphasis,
			 other.none,
			 other.context,
			 other.addition,
			 other.removal);
	}
	
}