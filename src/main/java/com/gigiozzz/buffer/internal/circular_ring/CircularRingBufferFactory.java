package com.gigiozzz.buffer.internal.circular_ring;

import java.io.Serializable;

import com.gigiozzz.buffer.BufferManager;
import com.gigiozzz.buffer.components.BufferFactory;
import com.gigiozzz.buffer.components.FlushStrategy;
import com.gigiozzz.buffer.components.PersistenceStrategy;

public final class CircularRingBufferFactory<T extends Serializable> implements BufferFactory<T> {

	private static CircularRingBufferFactory<?> instance = null;

	private CircularRingBufferFactory() {
	}

	@SuppressWarnings("unchecked")
	public static <C extends Serializable> CircularRingBufferFactory<C> getInstance(Class<C> c) {
		if (instance == null) {
			instance = new CircularRingBufferFactory<>();
		}
		return (CircularRingBufferFactory<C>) instance;
	}

	@Override
	public BufferManager<T> build(int bufferMaxSize, PersistenceStrategy<T> persistencer, FlushStrategy flusher) {
		return new CircularRingBufferImpl<>(bufferMaxSize, persistencer, flusher);
	}

}
