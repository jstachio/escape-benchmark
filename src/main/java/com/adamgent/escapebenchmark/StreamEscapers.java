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
					a.append(raw, start, i);
					a.append(found);
					start = i + 1;
				}
			}
			a.append(raw, start, end);
		}
	},
	SUBSTRING2() {
		@Override
		public void escape(
				Appendable a,
				String raw)
				throws IOException {
			int end = raw.length();
			for (int i = 0, start = 0; i < end; i++) {
				char c = raw.charAt(i);
				String found = escapeChar(c);
				/*
				 * While this could be done with one loop
				 * it appears through benchmarking that the
				 * by having hte first loop assume the string to be not 
				 * changed creates a fast path.
				 */
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
	
	public static StreamEscaper LOOKUP = Lookup7bitStreamEscaper.of();

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
	
	static class Lookup7bitStreamEscaper implements StreamEscaper {
		/*
		 * This only works for replacing the lower 7 bit ascii
		 * characters
		 */
		private final String[] lookupTable;
		
		private Lookup7bitStreamEscaper(
				String[] lookupTable) {
			super();
			this.lookupTable = lookupTable;
		}
		
		public static Lookup7bitStreamEscaper of() {
			String[][] mappings = new String[][] {
				{"\"",  QUOT},
				{ "&",  AMP},
				{ "\'",  APOS},
				{ "<",  LT},
				{ "=",  EQUAL},
				{ ">",  GT},
				{ "`",  BACK_TICK}
			};
			return of(mappings);
		}
		
		public static Lookup7bitStreamEscaper of(String[][] mappings) {
			String[] table = createTable(mappings);
			if (table == null) {
				throw new IllegalArgumentException("one of the characters is out of 127 range");
			}
			return new Lookup7bitStreamEscaper(table);
		}

		static /* @Nullable */ String[] createTable(String[][] mappings) {
			String[] table = new String[128];
			for (String[] entry : mappings) {
				String key = entry[0];
				String value = entry[1];
				if (key.length() != 1) {
					return null;
				}
				char k = key.charAt(0);
				if (k > 127) {
					return null;
				}
				table[k] = value;
			}
			return table;
		}

		@Override
		public void escape(
				Appendable a,
				String raw)
				throws IOException {
			int end = raw.length();
			for (int i = 0, start = 0; i < end; i++) {
				char c = raw.charAt(i);
				String found = escapeChar(lookupTable, c);
				/*
				 * While this could be done with one loop
				 * it appears through benchmarking that the
				 * by having hte first loop assume the string to be not 
				 * changed creates a fast path.
				 */
				if (found != null) {
					a.append(raw, 0, i);
					a.append(found);
					start = i = i + 1;
					for (; i < end; i++) {
						c = raw.charAt(i);
						found = escapeChar(lookupTable, c);
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
		
		private static /* @Nullable */ String escapeChar(String[] lookupTable, char c) {
			if (c > 127) {
				return null;
			}
			return lookupTable[c];
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
