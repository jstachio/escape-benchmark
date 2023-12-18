package com.adamgent.escapebenchmark;

import com.samskivert.mustache.Mustache.Escaper;

public enum Escapers implements Escaper {

	CHAR_AT {
		@Override
		public String escape(
				String csq) {
			return _escape1(csq);
		}
	},

	CHAR_AT_NO_ALLOCATE {
		@Override
		public String escape(
				String csq) {
			return _escape3(csq);
		}
	},

	SUBSTRING {
		@Override
		public String escape(
				String csq) {
			return _escape2(csq);
		}
	},
	SUBSTRING_NO_ALLOCATE {
		@Override
		public String escape(
				String csq) {
			return _escape4(csq);
		}
	},

	BEST {
		@Override
		public String escape(
				String raw) {
			int end = raw.length();
			for (int i = 0; i < end; i++) {
				char c = raw.charAt(i);
				String found = escapeChar(c);
				if (found != null) {
					StringBuilder a = new StringBuilder(end + 16);
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
					return a.toString();
				}
			}
			return raw;
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

	private static String _escape1(
			String csq) {
		int end = csq.length();
		StringBuilder a = new StringBuilder(end);
		int count = 0;
		for (int i = 0; i < end; i++) {
			char c = csq.charAt(i);
			switch (c) {
			case '"' -> { // 34
				a.append(QUOT);
			}
			case '&' -> { // 38
				a.append(AMP);
			}
			case '\'' -> { // 39
				a.append(APOS);
			}
			case '<' -> { // 60
				a.append(LT);
			}
			case '=' -> { // 61
				a.append(EQUAL);
			}
			case '>' -> { // 62
				a.append(GT);
			}
			case '`' -> { // 96
				a.append(BACK_TICK);
			}
			default -> {
				a.append(c);
				count++;
			}
			}
		}
		if (count == end) {
			return csq;
		}
		return a.toString();
	}

	private static String _escape2(
			String csq) {
		int start = 0;
		int end = csq.length();
		StringBuilder a = new StringBuilder(end);
		int count = 0;
		for (int i = start; i < end; i++) {
			char c = csq.charAt(i);
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
				count++;
			} // NOSONAR
			}
		}
		if (count == end) {
			return csq;
		}
		a.append(csq, start, end);
		return a.toString();
	}

	private static String _escape3(
			String csq) {
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
				StringBuilder a = new StringBuilder(end + 16);
				a.append(csq, 0, i);
				a.append(found);
				i++;
				for (; i < end; i++) {
					c = csq.charAt(i);
					switch (c) {
					case '"' -> a.append(QUOT);
					case '&' -> a.append(AMP);
					case '\'' -> a.append(APOS);
					case '<' -> a.append(LT);
					case '=' -> a.append(EQUAL);
					case '>' -> a.append(GT);
					case '`' -> a.append(BACK_TICK);
					default -> a.append(c);
					}
					;
				}
				return a.toString();
			}
		}
		return csq;
	}

	private static String _escape4(
			String csq) {
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
				StringBuilder a = new StringBuilder(end + 16);
				a.append(csq, 0, i);
				a.append(found);
				i++;
				int start = i;
				for (; i < end; i++) {
					c = csq.charAt(i);
					switch (c) {
					case '"' -> { // 34
						a.append(csq, start, i);
						a.append(QUOT);
						start = i + 1;
					}
					case '&' -> { // 38
						a.append(csq, start, i);
						a.append(AMP);
						start = i + 1;

					}
					case '\'' -> { // 39
						a.append(csq, start, i);
						a.append(APOS);
						start = i + 1;
					}
					case '<' -> { // 60
						a.append(csq, start, i);
						a.append(LT);
						start = i + 1;
					}
					case '=' -> { // 61
						a.append(csq, start, i);
						a.append(EQUAL);
						start = i + 1;
					}
					case '>' -> { // 62
						a.append(csq, start, i);
						a.append(GT);
						start = i + 1;
					}
					case '`' -> { // 96
						a.append(csq, start, i);
						a.append(BACK_TICK);
						start = i + 1;
					}
					default -> { // NOSONAR
					} // NOSONAR
					}
				}
				a.append(csq, start, end);
				return a.toString();
			}
		}
		return csq;
	}

	// protected byte p10, p11, p12, p13, p14, p15, p16, p17, p20, p21, p22,
	// p23, p24, p25, p26, p27, p30, p31, p32,
	// p33, p34, p35, p36, p37, p40, p41, p42, p43, p44, p45, p46, p47, p50,
	// p51, p52, p53, p54, p55, p56, p57,
	// p60, p61, p62, p63, p64, p65, p66, p67, p70, p71, p72, p73, p74, p75,
	// p76, p77;

	static final String QUOT = "&quot;";

	static final String AMP = "&amp;";

	//static final String APOS = "&#x27;";

	static final String APOS = "&#39;";

	static final String LT = "&lt;";

	static final String EQUAL = "&#x3D;";

	static final String GT = "&gt;";

	static final String BACK_TICK = "&#x60;";

}