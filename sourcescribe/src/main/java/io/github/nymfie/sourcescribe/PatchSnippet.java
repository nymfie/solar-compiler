package io.github.nymfie.sourcescribe;

import java.util.List;

public final class PatchSnippet extends Snippet<PatchSnippet, Patch> {

	public PatchSnippet(SharedSource source) {
		super(source);
	}
	
	public PatchSnippet patch(Patch patch) {
		return addOne(patch);
	}
	
	public PatchSnippet patches(List<? extends Patch> patches) {
		return addMany(patches);
	}
}