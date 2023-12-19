package com.adamgent.escapebenchmark;

import org.openjdk.jmh.annotations.Benchmark;

public class StreamEscaperBenchmark extends BaseBenchmark {
	
	@Benchmark
	public String streamJMustache() {
		return run(StreamEscapers.ORIGINAL);
	}
	
	@Benchmark
	public String streamCharAt() {
		return run(StreamEscapers.CHAR_AT);
	}
	
	@Benchmark
	public String streamSubstring() {
		return run(StreamEscapers.SUBSTRING);
	}
	
	@Benchmark
	public String streamSubstring2() {
		return run(StreamEscapers.SUBSTRING2);
	}
	
	@Benchmark
	public String streamLookup() {
		return run(StreamEscapers.LOOKUP);
	}
	
}
