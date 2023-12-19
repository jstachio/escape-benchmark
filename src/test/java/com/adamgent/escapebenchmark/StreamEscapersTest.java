package com.adamgent.escapebenchmark;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class StreamEscapersTest {

	private final StreamEscaper escaper;

	public StreamEscapersTest(
			StreamEscaper escaper,
			String name) {
		super();
		this.escaper = escaper;
	}

	@Test
	public void test() throws IOException {
		StringBuilder buffer = new StringBuilder();
		escaper.escape(buffer, "< hello > stuff ' and \" and ` and =");
		String actual = buffer.toString();
		String expected = "&lt; hello &gt; stuff &#39; and &quot; and &#x60; and &#x3D;";
		assertEquals(expected, actual);
	}

	@Parameters(name = "{1}")
	public static Collection<Object[]> data() {
		List<StreamEscaper> total = new ArrayList<>();
		total.addAll(EnumSet.allOf(StreamEscapers.class));
		total.add(StreamEscapers.LOOKUP);
		total.add(StreamEscapers.MUSTACHE_DOT_JAVA);
		return total.stream().map(o -> new Object[] {o, toString(o)}).toList();
	}
	
	private static String toString(StreamEscaper escaper) {
		if (escaper instanceof Enum<?> e) {
			return "stream-" + e.name();
		}
		return "stream-" + escaper.getClass().getName();
	}

}
