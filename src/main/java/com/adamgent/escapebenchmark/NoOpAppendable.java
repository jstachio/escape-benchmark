package com.adamgent.escapebenchmark;

public enum NoOpAppendable implements Appendable {
	INSTANCE;

	@Override
	public NoOpAppendable append(
			CharSequence csq) {
		return this;
	}

	@Override
	public NoOpAppendable append(
			CharSequence csq,
			int start,
			int end) {
		return this;
	}

	@Override
	public NoOpAppendable append(
			char c) {
		return this;
	}

}
