package com.adamgent.escapebenchmark;

import java.io.IOException;

public interface StreamEscaper {
	
	public void escape(Appendable a, String raw) throws IOException;

}
