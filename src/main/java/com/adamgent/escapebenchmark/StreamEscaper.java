package com.adamgent.escapebenchmark;

import java.io.IOException;
import java.io.Writer;

public interface StreamEscaper {
	
	public void escape(Appendable a, String raw) throws IOException;
	
	default void escape(Writer w, String raw) throws IOException {
		escape((Appendable) w, raw);
	}

}
