package com.adamgent.escapebenchmark;

import org.openjdk.jmh.annotations.Benchmark;

import com.samskivert.mustache.Escapers;

public class OriginalEscaper extends BaseBenchmark {

	@Benchmark
	public String benchmark() {
		return run(Escapers.HTML);
	}
}
