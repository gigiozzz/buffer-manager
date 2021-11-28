package com.gigiozzz.buffer;

import static com.gigiozzz.buffer.BufferManager.DEFAULT_CAPACITY;
import static com.gigiozzz.buffer.BufferManager.DEFAULT_PATH;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Path;
import java.util.Optional;

import com.gigiozzz.buffer.components.BufferBuilder;
import com.gigiozzz.buffer.internal.array_list.ArrayListBufferFactory;
import com.gigiozzz.buffer.strategy.FlushStartegyControlFlushImpl;
import com.gigiozzz.buffer.strategy.PersistenceStrategyFileJsonImpl;

public class DefaultListBuffer {

	private DefaultListBuffer() {
	}

	public static <T extends Serializable> BufferManager<T> build(Class<T> clazz, Optional<Integer> capacity,
			Optional<Path> path) throws IOException {
		return BufferBuilder.get(clazz).withCapacity(capacity.orElse(DEFAULT_CAPACITY))
				.withFlushStrategy(new FlushStartegyControlFlushImpl())
				.withPersistenceStrategy(new PersistenceStrategyFileJsonImpl<>(path.orElse(DEFAULT_PATH), clazz))
				.build(ArrayListBufferFactory.getInstance(clazz));
	}

	public static <T extends Serializable> BufferManager<T> build(Class<T> clazz) throws IOException {
		return build(clazz, Optional.of(DEFAULT_CAPACITY), Optional.of(DEFAULT_PATH));
	}

}
