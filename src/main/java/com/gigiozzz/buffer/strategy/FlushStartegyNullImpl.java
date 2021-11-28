package com.gigiozzz.buffer.strategy;

import com.gigiozzz.buffer.components.FlushStrategy;

public class FlushStartegyNullImpl implements FlushStrategy {

	@Override
	public void flush(Runnable save) {
		// do nothing
	}

	@Override
	public void flushAfterBuf(Runnable save) {
		// do nothing
	}

	@Override
	public void flushAfterDebuf(Runnable save) {
		// do nothing
	}


}
