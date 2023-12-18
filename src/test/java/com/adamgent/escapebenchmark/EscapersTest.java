package com.adamgent.escapebenchmark;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.samskivert.mustache.Mustache.Escaper;

@RunWith(Parameterized.class)
public class EscapersTest {

	private final Escaper escaper;

	public EscapersTest(
			Escaper escaper,
			String name) {
		super();
		this.escaper = escaper;
	}

	@Test
	public void test() {
		String actual = escaper.escape("< hello > stuff ' and \" and ` and =");
		String expected = "&lt; hello &gt; stuff &#39; and &quot; and &#x60; and &#x3D;";
		assertEquals(expected, actual);
	}

	@Parameters(name = "{1}")
	public static Collection<Object[]> data() {
		List<Escaper> total = new ArrayList<>();
		total.addAll(EnumSet.allOf(Escapers.class));
		total.add(com.samskivert.mustache.Escapers.HTML);
		return total.stream().map(o -> new Object[] {o, toString(o)}).toList();
	}
	
	private static String toString(Escaper escaper) {
		if (escaper instanceof Enum<?> e) {
			return "escaper-" + e.name();
		}
		return "escaper-" + escaper.getClass().getName();
	}

}
