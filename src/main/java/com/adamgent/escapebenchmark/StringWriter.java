package com.adamgent.escapebenchmark;

import java.io.IOException;
import java.io.Writer;

public class StringWriter extends Writer {
	private final StringBuilder buf;

	/**
	 * Create a new string writer using the default initial string-buffer size.
	 */
	StringWriter(
			StringBuilder buf) {
		this.buf = buf;
		lock = buf;
	}

	/**
	 * Write a single character.
	 */
	public void write(
			int c) {
		buf.append((char) c);
	}

	public void write(
			char cbuf[],
			int off,
			int len) {
		if ((off < 0) || (off > cbuf.length) || (len < 0) || ((off + len) > cbuf.length) || ((off + len) < 0)) {
			throw new IndexOutOfBoundsException();
		} else if (len == 0) {
			return;
		}
		buf.append(cbuf, off, len);
	}

	/**
	 * Write a string.
	 */
	public void write(
			String str) {
		buf.append(str);
	}

	public void write(
			String str,
			int off,
			int len) {
		buf.append(str, off, off + len);
	}

	public StringWriter append(
			CharSequence csq) {
		write(String.valueOf(csq));
		return this;
	}

	public StringWriter append(
			CharSequence csq,
			int start,
			int end) {
		if (csq == null)
			csq = "null";
		return append(csq.subSequence(start, end));
	}

	public StringWriter append(
			char c) {
		write(c);
		return this;
	}

	/**
	 * Return the string buffer itself.
	 * 
	 * @return StringBuffer holding the current buffer value.
	 */
	public StringBuilder getBuffer() {
		return buf;
	}
	
	public String toString() {
		return buf.toString();
	}

	public void flush() {
	}

	/**
	 * Closing a {@code StringWriter} has no effect. The methods in this class
	 * can be called after the stream has been closed without generating an
	 * {@code IOException}.
	 */
	public void close()
			throws IOException {
	}
}
