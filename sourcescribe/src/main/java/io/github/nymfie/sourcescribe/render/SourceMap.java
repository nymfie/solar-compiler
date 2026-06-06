package io.github.nymfie.sourcescribe.render;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.jspecify.annotations.Nullable;

import io.github.nymfie.sourcescribe.ByteSpan;
import io.github.nymfie.sourcescribe.SharedSource;

final class SourceMap {
	final static Charset ENCODING = Charset.forName("UTF-8");
	final List<LineInfo> lines;
	final SharedSource source;
	final byte[] sourceBytes;
	
	SourceMap(SharedSource source, long lineStart) {
		this.source = Objects.requireNonNull(source);
		sourceBytes = source.source().getBytes(ENCODING);
		
		// Handle an empty source
		if (source.source().isEmpty()) {
			lines = List.of(new LineInfo("", lineStart, new ByteSpan(0, 0), LineEnd.EOF));
			return;
		}
		
		/* BEWARE! UGLY CODE AHEAD!! */
		// Otherwise go line by line
		long byteIndex = 0;
		long lineIndex = lineStart;
		lines = new ArrayList<LineInfo>();
		String tempSource = source.source();
		while (!tempSource.isEmpty()) {
			// Unfortunately (or perhaps not), Java only supports 32-bit array indices
			if (lines.size() == Integer.MAX_VALUE-1)
				throw new ArrayIndexOutOfBoundsException("SourceMap source cannot exceed %d lines".formatted(Integer.MAX_VALUE-1));
			int nextNewline = tempSource.indexOf('\n');
			String line;
			LineEnd lineEnd;
			
			// Eof
			if (nextNewline == -1)
			{
				line = tempSource;
				lineEnd = LineEnd.EOF;
			// \r\n (CRLF)
			} else if (Integer.compareUnsigned(0, nextNewline) < 0 && tempSource.charAt(nextNewline-1) == '\r') {
				line = tempSource.substring(0, nextNewline-1);
				lineEnd = LineEnd.CRLF;
			// \n (LF)
			} else {
				line = tempSource.substring(0, nextNewline);
				lineEnd = LineEnd.LF;
			}
			int byteLen = line.getBytes(ENCODING).length;
			lines.add(new LineInfo(line, lineIndex, new ByteSpan(byteIndex, byteIndex + byteLen), lineEnd));
			tempSource = tempSource.substring(nextNewline+1);
			byteIndex += byteLen + lineEnd.length();
			lineIndex++;
		}
	}
	
	@Nullable String tryGetLine(long lineIndex) {
		// Base case, 0 lines
		if (lines.size() == 0)
			return null;
		
		// Adjust relative to first index
		lineIndex -= lines.getFirst().lineIndex();
		
		// Check that we actually include the line
		if (Long.compareUnsigned(lineIndex, Integer.toUnsignedLong(lines.size())) < 0)
			return lines.get((int)lineIndex).line();
		
		// Otherwise return null
		return null;
	}
	
	Bounds spanToLocations(ByteSpan span) {
		// TODO
		throw new UnsupportedOperationException("Not yet implemented!");
	}
	
	@Nullable String trySpanToSnippets(ByteSpan span) {
		
	}
}

enum LineEnd {
	EOF(0), LF(1), CRLF(2);
	
	private long length;
	private LineEnd(long length) { this.length = length; }
	
	public long length() { return length; }
}

record Loc(long lineNumber, long colCharIndex, long colDisplayIndex, long byteOffset) { }
record Bounds(Loc start, Loc last) { }

record LineInfo(String line, long lineIndex, ByteSpan lineSpan, LineEnd lineEnd) {
	LineInfo {
		line = Objects.requireNonNull(line);
		lineSpan = Objects.requireNonNull(lineSpan);
		lineEnd = Objects.requireNonNull(lineEnd);
	}
}

record TrimmedPatch(ByteSpan originalSpan, ByteSpan span, String replacement) {
	TrimmedPatch {
		originalSpan = Objects.requireNonNull(originalSpan);
		span = Objects.requireNonNull(span);
		replacement = Objects.requireNonNull(replacement);
	}
	
	boolean isAddition(SourceMap sourceMap) {
		return !replacement.isEmpty() && !replacesMeaningfulContent(sourceMap);
	}
	
	boolean isRemoval(SourceMap sourceMap) {
		return replacement.isBlank() && replacesMeaningfulContent(sourceMap);
	}
	
	boolean isReplacement(SourceMap sourceMap) {
		return !replacement.isEmpty() && replacesMeaningfulContent(sourceMap);
	}
	
	boolean isDestructiveReplacement(SourceMap sourceMap) {
		return isReplacement(sourceMap) &&
				sourceMap.spanToSnippet(span)
	}
}