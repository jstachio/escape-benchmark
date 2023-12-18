package com.adamgent.escapebenchmark;

import org.openjdk.jmh.annotations.Benchmark;

public class StreamEscaperBenchmark extends BaseBenchmark {
	
	@Benchmark
	public String streamOriginal() {
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
}
