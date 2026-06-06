package io.github.nymfie.sourcescribe.render;

import java.util.List;

import io.github.nymfie.sourcescribe.Group;

public final class Renderer {
	private Renderer() { }
	
	/**
	 * Render an ordered group of diagnostic messages according to the renderer settings
	 * @param groups - the ordered list of groups to render
	 * @return a formatted string representing the render output
	 */
	public String render(RendererSettings settings, List<Group> groups) {
		if (settings.shortMessage)
			return renderShortMessage(settings, groups);
		// TODO
		throw new UnsupportedOperationException("Not implemented yet!");
	}
	
	/**
	 * Render and return a message in short form
	 */
	private String renderShortMessage(RendererSettings settings, List<Group> groups) {
		// TODO
		throw new UnsupportedOperationException("Not implemented yet!");
	}
	
	private void renderTitleToBuffer(RendererSettings settings) {
		// TODO
		throw new UnsupportedOperationException("Not implemented yet!");
	}
	
	private void renderOriginToBuffer(RendererSettings settings) {
		// TODO
		throw new UnsupportedOperationException("Not implemented yet!");
	}
	
	private void renderSnippetToBuffer(RendererSettings settings) {
		// TODO
		throw new UnsupportedOperationException("Not implemented yet!");
	}
	
	private void renderSourceLineToBuffer(RendererSettings settings) {
		// TODO
		throw new UnsupportedOperationException("Not implemented yet!");
	}
}
