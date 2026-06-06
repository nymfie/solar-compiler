package io.github.nymfie.sourcescribe.render;

import java.util.ArrayList;
import java.util.List;

final class StyledBuffer {
	List<List<StyledChar>> lines = new ArrayList<List<StyledChar>>();
}

record StyledChar(char ch, ElementStyle style) {	
	final static StyledChar SPACE = new StyledChar(' ', ElementStyle.NO_STYLE);
}