package com.gigiozzz.buffer.tests.utils;

import java.util.ArrayList;
import java.util.List;

import com.gigiozzz.buffer.components.FlushStrategy;
import com.gigiozzz.buffer.components.PersistenceStrategy;

public class BufferBuilderPersistenceStrategyTest implements PersistenceStrategy<TestObj> {

	@Override
	public void save(List<TestObj> list) {
		throw new IllegalArgumentException();
	}

	@Override
	public List<TestObj> load() {
		return new ArrayList<>();
	}


	public static class BufferBuilderInternalFlushStrategyTest implements FlushStrategy {

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
			save.run();
		}

	}

}
