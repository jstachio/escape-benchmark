package com.adamgent.escapebenchmark;

import java.io.IOException;
import java.io.Writer;
import java.nio.CharBuffer;


public class AppendableWriter extends Writer {

	private final Appendable buf;

	/**
	 * Create a new string writer using the default initial string-buffer size.
	 */
	AppendableWriter(Appendable buf) {
		this.buf = buf;
		lock = buf;
	}

	/**
	 * Write a single character.
	 * @throws IOException 
	 */
	public void write(int c) throws IOException {
		buf.append((char) c);
	}

	public void write(char cbuf[], int off, int len) throws IOException {
		if ((off < 0) || (off > cbuf.length) || (len < 0) || ((off + len) > cbuf.length) || ((off + len) < 0)) {
			throw new IndexOutOfBoundsException();
		}
		else if (len == 0) {
			return;
		}
		if (buf instanceof StringBuilder sb) {
			sb.append(cbuf, off, len);
		}
		else if (buf instanceof Writer w) {
			w.write(cbuf, off, len);
		}
		else {
			buf.append(CharBuffer.wrap(cbuf), off, len);
		}
	}

	/**
	 * Write a string.
	 * @throws IOException 
	 */
	public void write(String str) throws IOException {
		buf.append(str);
	}

	public void write(String str, int off, int len) throws IOException {
		buf.append(str, off, off + len);
	}

	public AppendableWriter append(CharSequence csq) throws IOException {
		buf.append(csq);
		return this;
	}

	public AppendableWriter append(CharSequence csq, int start, int end) throws IOException {
		buf.append(csq, start, end);
		return this;
	}

	public AppendableWriter append(char c) throws IOException {
		buf.append(c);
		return this;
	}

	public void flush() throws IOException {
	}

	/**
	 * Closing a {@code StringWriter} has no effect. The methods in this class can be
	 * called after the stream has been closed without generating an {@code IOException}.
	 */
	public void close() throws IOException {
	}
}
