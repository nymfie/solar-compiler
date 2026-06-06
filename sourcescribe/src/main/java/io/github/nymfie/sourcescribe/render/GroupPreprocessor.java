package io.github.nymfie.sourcescribe.render;

import java.util.List;

record PreprocessedResult(long maxLineNumber, String originalPrimaryPath, List<PreprocessedGroup> groups) {
	
}

final class GroupPreprocessor {
	private GroupPreprocessor() { }
}

