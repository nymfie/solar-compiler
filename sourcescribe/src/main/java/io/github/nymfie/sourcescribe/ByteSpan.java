package io.github.nymfie.sourcescribe;

/**
 * Represents a positive span of bytes with an included start and excluded end.<br>
 * <br>
 * If end < start, returns the empty span at start<br>
 * If start == end, the span is considered empty.
 * 
 * @param start (unsigned long) - the start of the span, inclusive
 * @param end   (unsigned long) - the end of the span, exclusive
 */
public record ByteSpan(long start, long end) implements Comparable<ByteSpan> {
	public ByteSpan {
		// If end < start, normalize to empty range (start == end)
		if (end < start)
			end = start;
	}

	public boolean isEmpty() {
		return start == end;
	}

	public boolean contains(long value) {
		return (Long.compareUnsigned(start, value) <= 0) && (Long.compareUnsigned(value, end) < 0);
	}

	@Override
	public int compareTo(ByteSpan o) {
		return Long.compareUnsigned(start, o.start);
	}

	@Override
	public String toString() {
		return "Range(%s..%s, size=%s)".formatted(
			Long.toUnsignedString(start),
			Long.toUnsignedString(end),
			Long.toUnsignedString(end-start)
		);
	}
}
