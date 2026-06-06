package io.github.nymfie.sourcescribe.render;

import java.util.List;
import java.util.Objects;

import io.github.nymfie.sourcescribe.Group;
import io.github.nymfie.sourcescribe.ansi.Style;

public final class RendererSettings {
	boolean anonymizedLineNumbers;
	long termWidth;
	DecorStyle decorStyle;
	Stylesheet stylesheet;
	boolean shortMessage;
	
	private RendererSettings(boolean anonymizedLineNumbers, long termWidth, DecorStyle decorStyle, Stylesheet stylesheet, boolean shortMessage) {
		this.anonymizedLineNumbers = anonymizedLineNumbers;
		this.termWidth = termWidth;
		this.decorStyle = Objects.requireNonNull(decorStyle);
		this.stylesheet = new Stylesheet(stylesheet);
		this.shortMessage = shortMessage;
	}
	
	public RendererSettings(RendererSettings other) {
		this(other.anonymizedLineNumbers, other.termWidth, other.decorStyle, other.stylesheet, other.shortMessage);
	}

	/**
	 * Create a plain renderer with no terminal styling
	 * @see RendererSettings#styled()
	 */
	public static RendererSettings plain() {
		return new RendererSettings(false, Defaults.DEFAULT_TERM_WIDTH, DecorStyle.ASCII, new Stylesheet(), false);
	}
	
	/**
	 * Create a renderer with default terminal styling
	 * @see RendererSettings#plain()
	 */
	public static RendererSettings styled() {
		return new RendererSettings(false, Defaults.DEFAULT_TERM_WIDTH, DecorStyle.ASCII, Defaults.DEFAULT_STYLESHEET, false);
	}
	
	public RendererSettings anonymizedLineNumbers(boolean newAnonymizedLineNumbers) {
		anonymizedLineNumbers = newAnonymizedLineNumbers;
		return this;
	}
	public boolean anonymizedLineNumbers() {
		return anonymizedLineNumbers;
	}
	
	public RendererSettings termWidth(long newTermWidth) {
		termWidth = newTermWidth;
		return this;
	}
	public long termWidth() {
		return termWidth;
	}
	
	public RendererSettings decorStyle(DecorStyle newDecorStyle) {
		decorStyle = Objects.requireNonNull(newDecorStyle);
		return this;
	}
	public DecorStyle decorStyle() {
		return decorStyle;
	}
	
	public RendererSettings stylesheet(Stylesheet stylesheet) {
		stylesheet = new Stylesheet(stylesheet);
		return this;
	}
	public Stylesheet stylesheet() {
		return new Stylesheet();
	}
	
	public RendererSettings shortMessage(boolean newShortMessage) {
		shortMessage = newShortMessage;
		return this;
	}
	public boolean shortMessage() {
		return shortMessage;
	}
}
