package com.gigiozzz.buffer.internal.array_list;

import java.io.Serializable;

import com.gigiozzz.buffer.BufferManager;
import com.gigiozzz.buffer.components.BufferFactory;
import com.gigiozzz.buffer.components.FlushStrategy;
import com.gigiozzz.buffer.components.PersistenceStrategy;

public final class ArrayListBufferFactory<T extends Serializable> implements BufferFactory<T> {

	private static ArrayListBufferFactory<?> instance = null;

	private ArrayListBufferFactory() {
	}

	@SuppressWarnings("unchecked")
	public static <C extends Serializable> ArrayListBufferFactory<C> getInstance(Class<C> c) {
		if (instance == null) {
			instance = new ArrayListBufferFactory<>();
		}
		return (ArrayListBufferFactory<C>) instance;
	}

	@Override
	public BufferManager<T> build(int bufferMaxSize, PersistenceStrategy<T> persistencer, FlushStrategy flusher) {
		return new ArrayListBufferImpl<>(bufferMaxSize, persistencer, flusher);
	}

}
