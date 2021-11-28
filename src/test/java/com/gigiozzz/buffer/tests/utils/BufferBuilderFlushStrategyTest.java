package com.gigiozzz.buffer.tests.utils;

import com.gigiozzz.buffer.components.FlushStrategy;

public class BufferBuilderFlushStrategyTest implements FlushStrategy {

	@Override
	public void flush(Runnable save) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void flushAfterBuf(Runnable save) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void flushAfterDebuf(Runnable save) {
		throw new UnsupportedOperationException();
	}


}
