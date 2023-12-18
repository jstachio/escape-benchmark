package com.adamgent.escapebenchmark;

import org.openjdk.jmh.annotations.Benchmark;

public class EscaperBenchmark extends BaseBenchmark {
	
	@Benchmark
	public String charAt() {
		return run(Escapers.CHAR_AT);
	}
	
	@Benchmark
	public String charAtNoAllocate() {
		return run(Escapers.CHAR_AT_NO_ALLOCATE);
	}

	@Benchmark
	public String substring() {
		return run(Escapers.SUBSTRING);
	}
	
	@Benchmark
	public String substringNoAllocate() {
		return run(Escapers.SUBSTRING_NO_ALLOCATE);
	}
	
	@Benchmark
	public String best() {
		return run(Escapers.BEST);
	}
}
