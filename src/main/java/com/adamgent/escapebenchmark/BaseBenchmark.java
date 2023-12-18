package com.adamgent.escapebenchmark;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Threads;
import org.openjdk.jmh.annotations.Warmup;

import com.samskivert.mustache.Mustache.Escaper;


@Fork(1)
@Warmup(iterations = 1)
@Measurement(iterations = 3)
@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.SECONDS)
@State(Scope.Benchmark)
@Threads(16)
public class BaseBenchmark {
	
	protected static final String DATA1 = """
			<script src="bad">STUFFF HELLO</script>
			""";
	
	protected static final String NUMBER = "300.00";
	
	protected static final String DATA = """
			fqwefqwefqwefqewfqwef
			qwefqwefqwef &
			qwefqwefqwefqwefwqefwqef
			
			eewefwewefeeeeeeeewerrgvwebre
			""";
	
	protected static final String run(Escaper escaper) {
		StringBuilder sb = new StringBuilder();
		sb.append(escaper.escape(DATA1));
		sb.append(escaper.escape(NUMBER));
		return sb.append(escaper.escape(DATA)).toString();
	}
	
	protected static final String run(StreamEscaper escaper) {
		try {
			StringBuilder sb = new StringBuilder();
			escaper.escape(sb, DATA1);
			escaper.escape(sb, NUMBER);
			escaper.escape(sb, DATA);
			return sb.toString();
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
	}

}
