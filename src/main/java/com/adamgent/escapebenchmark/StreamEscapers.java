package com.adamgent.escapebenchmark;

import java.io.IOException;
import java.io.Writer;

import com.samskivert.mustache.Escapers;

public enum StreamEscapers implements StreamEscaper {
	JMUSTACHE() {
		@Override
		public void escape(
				Appendable a,
				String raw)
				throws IOException {
			a.append(Escapers.HTML.escape(raw));
		}
	},
	CHAR_EXTERNAL_SWITCH() {
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
					a.append(found);
				}
				else {
					a.append(c);
				}
			}
		}
	},
	CHAR_EXTERNAL_SWITCH2() {
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
	SUBSTRING_INLINE_SWITCH() {
		@Override
		public void escape(
				Appendable a,
				String csq)
				throws IOException {
			int start = 0;
			int end = csq.length();
			for (int i = start; i < end; i++) {
				char c = csq.charAt(i);
				switch (c) {
				case '"' -> { // 34
					a.append(csq, start, i);
					start = i + 1;
					a.append(QUOT);
					//start = appendAndIncrement(a, csq, start, i, QUOT);
				}
				case '&' -> { // 38
					a.append(csq, start, i);
					start = i + 1;
					a.append(AMP);
					//start = appendAndIncrement(a, csq, start, i, AMP);
				}
				case '\'' -> { // 39
					a.append(csq, start, i);
					start = i + 1;
					a.append(APOS);
					//start = appendAndIncrement(a, csq, start, i, APOS);

				}
				case '<' -> { // 60
					a.append(csq, start, i);
					start = i + 1;
					a.append(LT);
					//start = appendAndIncrement(a, csq, start, i, LT);

				}
				case '=' -> { // 61
					a.append(csq, start, i);
					start = i + 1;
					a.append(EQUAL);
					//start = appendAndIncrement(a, csq, start, i, EQUAL);

				}
				case '>' -> { // 62
					a.append(csq, start, i);
					start = i + 1;
					a.append(GT);
					//start = appendAndIncrement(a, csq, start, i, GT);

				}
				case '`' -> { // 96
					a.append(csq, start, i);
					start = i + 1;
					a.append(BACK_TICK);
					//start = appendAndIncrement(a, csq, start, i, BACK_TICK);

				}
				default -> { // NOSONAR
				} // NOSONAR
				}
			}
			a.append(csq, start, end);
		}
	},
	SUBSTRING_INLINE_SWITCH2() {
		@Override
		public void escape(
				Appendable a,
				String csq)
				throws IOException {
			int end = csq.length();
			for (int i = 0; i < end; i++) {
				char c = csq.charAt(i);
				String found = switch (c) {
				case '"' -> QUOT;
				case '&' -> AMP;
				case '\'' -> APOS;
				case '<' -> LT;
				case '=' -> EQUAL;
				case '>' -> GT;
				case '`' -> BACK_TICK;
				default -> null;
				};
				if (found != null) {
					a.append(csq, 0, i);
					a.append(found);
					i++;
					int start = i;
					for (; i < end; i++) {
						c = csq.charAt(i);
						switch (c) {
						case '"' -> { // 34
							a.append(csq, start, i);
							start = i + 1;
							a.append(QUOT);
						}
						case '&' -> { // 38
							a.append(csq, start, i);
							start = i + 1;
							a.append(AMP);
						}
						case '\'' -> { // 39
							a.append(csq, start, i);
							start = i + 1;
							a.append(APOS);
						}
						case '<' -> { // 60
							a.append(csq, start, i);
							start = i + 1;
							a.append(LT);
						}
						case '=' -> { // 61
							a.append(csq, start, i);
							start = i + 1;
							a.append(EQUAL);
						}
						case '>' -> { // 62
							a.append(csq, start, i);
							start = i + 1;
							a.append(GT);
						}
						case '`' -> { // 96
							a.append(csq, start, i);
							start = i + 1;
							a.append(BACK_TICK);
						}
						default -> { // NOSONAR
						} // NOSONAR
						}
					}
					a.append(csq, start, i);
					return;
					
				}
			}
			a.append(csq);
		}
	},
	SUBSTRING_EXTERNAL_SWITCH() {
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
	SUBSTRING_EXTERNAL_SWITCH2() {
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
				 * While this could be done with one loop it appears through
				 * benchmarking that by having the first loop assume the string
				 * to be not changed creates a fast path for strings with no
				 * escaping needed.
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
	
//	private static int appendAndIncrement(Appendable a, String raw, int start, int i, String escaped) throws IOException {
//		a.append(raw, start, i);
//		a.append(escaped);
//		return i + 1;
//	}
	
	public static StreamEscaper LOOKUP2 = Lookup7bitStreamEscaper.of();
	
	public static StreamEscaper MUSTACHE_DOT_JAVA = new MustacheDotJava();

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
				 * While this could be done with one loop it appears through
				 * benchmarking that by having the first loop assume the string
				 * to be not changed creates a fast path for strings with no
				 * escaping needed.
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
	
	static class MustacheDotJava implements StreamEscaper {
		private static char[][] ESC = new char[97][];

		static {
			char[] AMP = "&amp;".toCharArray();
			char[] LT = "&lt;".toCharArray();
			char[] GT = "&gt;".toCharArray();
			char[] DQ = "&quot;".toCharArray();
			char[] SQ = "&#39;".toCharArray();
			char[] BQ = "&#x60;".toCharArray();
			char[] EQ = "&#x3D;".toCharArray();
			for (int c = 0; c < ESC.length; c++) {
				if (c <= 13) {
					ESC[c] = ("&#" + c + ";").toCharArray();
				} else {
					switch (c) {
					case '&':
						ESC[c] = AMP;
						break;
					case '<':
						ESC[c] = LT;
						break;
					case '>':
						ESC[c] = GT;
						break;
					case '"':
						ESC[c] = DQ;
						break;
					case '\'':
						ESC[c] = SQ;
						break;
					case '=':
						ESC[c] = EQ;
						break;
					case '`':
						ESC[c] = BQ;
						break;
					default:
						ESC[c] = null;
						break;
					}
				}
			}
		}
		
		@Override
		public void escape(
				Appendable a,
				String raw)
				throws IOException {
			escape(raw, new AppendableWriter(a));
		}
		
		@Override
		public void escape(
				Writer w,
				String raw)
				throws IOException {
			escape(raw, w);
		}

		public static void escape(
				String value,
				Writer writer)
				throws IOException {
			char[] chars = value.toCharArray();
			int length = chars.length;
			int start = 0;
			for (int i = 0; i < length; i++) {
				char c = chars[i];
				char[] escaped;
				// We only possibly escape chars in the range 0-96
				if (c <= 96 && (escaped = ESC[c]) != null) {
					// Write from the last replacement to before this one
					if (i > start)
						writer.write(chars, start, i - start);
					// Write the replacement
					writer.write(escaped);
					// Move the pointer to the position after replacement
					start = i + 1;
				}
			}
			writer.write(chars, start, length - start);

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
