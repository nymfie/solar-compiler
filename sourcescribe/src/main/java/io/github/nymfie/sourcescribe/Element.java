package io.github.nymfie.sourcescribe;

import java.util.Objects;

public sealed interface Element {	
	record MessageElement(Message message) implements Element {
		public MessageElement {
			message = Objects.requireNonNull(message);
		}
	}
	
	record CauseElement(AnnotationSnippet cause) implements Element {
		public CauseElement {
			cause = Objects.requireNonNull(cause);
		}
	}
	
	record SuggestionElement(PatchSnippet suggestion) implements Element {
		public SuggestionElement {
			suggestion = Objects.requireNonNull(suggestion);
		}
	}
	
	record OriginElement(Origin origin) implements Element {
		public OriginElement {
			origin = Objects.requireNonNull(origin);
		}
	}
	
	record PaddingElement() implements Element {}
}
