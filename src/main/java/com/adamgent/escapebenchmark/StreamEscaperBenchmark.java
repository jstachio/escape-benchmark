package com.adamgent.escapebenchmark;

import org.openjdk.jmh.annotations.Benchmark;

public class StreamEscaperBenchmark extends BaseBenchmark {
	
	@Benchmark
	public String streamJMustache() {
		return run(StreamEscapers.JMUSTACHE);
	}
	
	@Benchmark
	public String streamCharExternalSwitch() {
		return run(StreamEscapers.CHAR_EXTERNAL_SWITCH);
	}
	
	@Benchmark
	public String streamCharExternalSwitch2() {
		return run(StreamEscapers.CHAR_EXTERNAL_SWITCH2);
	}
	
	@Benchmark
	public String streamSubstringInlineSwitch() {
		return run(StreamEscapers.SUBSTRING_INLINE_SWITCH);
	}
	
	@Benchmark
	public String streamSubstringInlineSwitch2() {
		return run(StreamEscapers.SUBSTRING_INLINE_SWITCH2);
	}
	
	@Benchmark
	public String streamSubstringExternalSwitch() {
		return run(StreamEscapers.SUBSTRING_EXTERNAL_SWITCH);
	}
	
	@Benchmark
	public String streamSubstringExternalSwitch2() {
		return run(StreamEscapers.SUBSTRING_EXTERNAL_SWITCH2);
	}
	
	@Benchmark
	public String streamLookup2() {
		return run(StreamEscapers.LOOKUP2);
	}
	
	@Benchmark
	public String streamMustacheJava() {
		return runWriter(StreamEscapers.MUSTACHE_DOT_JAVA);
	}
	
}
