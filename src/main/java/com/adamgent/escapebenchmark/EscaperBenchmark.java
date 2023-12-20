package com.adamgent.escapebenchmark;

import org.openjdk.jmh.annotations.Benchmark;

public class EscaperBenchmark extends BaseBenchmark {
	
	@Benchmark
	public String jmustache() {
		return run(com.samskivert.mustache.Escapers.HTML);
	}
	
	@Benchmark
	public String charExternalSwitch2() {
		return run(Escapers.CHAR_EXTERNAL_SWITCH2);
	}
	
	@Benchmark
	public String charInlineSwitch() {
		return run(Escapers.CHAR_INLINE_SWITCH);
	}
	
	@Benchmark
	public String charSwitchPreAllocate() {
		return run(Escapers.CHAR_INLINE_SWITCH_PRE_ALLOCATE);
	}

	@Benchmark
	public String substringInlineSwitchPreAllocate() {
		return run(Escapers.SUBSTRING_INLINE_SWITCH_PRE_ALLOCATE);
	}
	
	@Benchmark
	public String substringInlineSwitch2() {
		return run(Escapers.SUBSTRING_INLINE_SWITCH2);
	}
	
	@Benchmark
	public String substringExternalSwitch2() {
		return run(Escapers.SUBSTRING_EXTERNAL_SWITCH2);
	}

}
