package com.adamgent.escapebenchmark;

import java.io.IOException;

import com.samskivert.mustache.Escapers;

public enum StreamEscapers implements StreamEscaper {
	ORIGINAL() {
		@Override
		public void escape(
				Appendable a,
				String raw)
				throws IOException {
			a.append(Escapers.HTML.escape(raw));
		}
	},
	CHAR_AT() {
		@Override
		public void escape(
				Appendable a,
				String raw)
				throws IOException {
			int end = raw.length();
			for (int i = 0; i < end; i++) {
				char c = raw.charAt(i);
				String found = escapeChar(c);
				if (found != null) {
					a.append(raw, 0, i);
					a.append(found);
					i++;
					for (; i < end; i++) {
						c = raw.charAt(i);
						found = escapeChar(c);
						if (found != null) {
							a.append(found);
						} else {
							a.append(c);
						}
					}
					return;
				}
			}
			a.append(raw);
		}
	},
	SUBSTRING() {
		@Override
		public void escape(
				Appendable a,
				String raw)
				throws IOException {
			int start = 0;
			int end = raw.length();
			for (int i = start; i < end; i++) {
				char c = raw.charAt(i);
				String found = escapeChar(c);
				if (found != null) {
					a.append(raw, 0, i);
					a.append(found);
					start = i = i + 1;
					for (; i < end; i++) {
						c = raw.charAt(i);
						found = escapeChar(c);
						if (found != null) {
							a.append(raw, start, i);
							a.append(found);
							start = i + 1;
						}
					}
					a.append(raw, start, end);
					return;
				}
			}
			a.append(raw);
		}
	};

	private static String escapeChar(
			char c) {
		switch (c) {
		case '"':
			return QUOT;
		case '&':
			return AMP;
		case '\'':
			return APOS;
		case '<':
			return LT;
		case '=':
			return EQUAL;
		case '>':
			return GT;
		case '`':
			return BACK_TICK;
		default:
			return null;
		}
	}

	private static final String QUOT = "&quot;";

	private static final String AMP = "&amp;";

	//private static final String APOS = "&#x27;";
	
	private static final String APOS = "&#39;";

	private static final String LT = "&lt;";

	private static final String EQUAL = "&#x3D;";

	private static final String GT = "&gt;";

	private static final String BACK_TICK = "&#x60;";

}
