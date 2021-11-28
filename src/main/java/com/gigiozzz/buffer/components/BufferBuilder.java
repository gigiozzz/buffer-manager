package com.gigiozzz.buffer.components;

import java.io.Serializable;

import com.gigiozzz.buffer.BufferManager;
import com.gigiozzz.buffer.strategy.FlushStartegyNullImpl;
import com.gigiozzz.buffer.strategy.PersistenceStartegyNullImpl;

public class BufferBuilder<T extends Serializable> {

	private int capacity = BufferManager.DEFAULT_CAPACITY;
	private FlushStrategy flushStrategy = new FlushStartegyNullImpl();
	@SuppressWarnings("unchecked")
	private PersistenceStrategy<T> persistencer = (PersistenceStrategy<T>) new PersistenceStartegyNullImpl();

	public static <C extends Serializable> BufferBuilder<C> get(Class<C> c) {
		return new BufferBuilder<>();
	}

	public BufferManager<T> build(BufferFactory<T> factory) {
		return factory.build(capacity, persistencer, flushStrategy);
	}

	public BufferBuilder<T> withCapacity(int capacity) {
		this.capacity = capacity;
		return this;
	}

	public BufferBuilder<T> withFlushStrategy(FlushStrategy flushStrategy) {
		this.flushStrategy = flushStrategy;
		return this;
	}

	public BufferBuilder<T> withPersistenceStrategy(PersistenceStrategy<T> persistencer) {
		this.persistencer = persistencer;
		return this;
	}

}
