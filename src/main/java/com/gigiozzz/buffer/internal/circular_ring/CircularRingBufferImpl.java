package com.gigiozzz.buffer.internal.circular_ring;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import com.gigiozzz.buffer.BufferFullException;
import com.gigiozzz.buffer.BufferManager;
import com.gigiozzz.buffer.components.FlushStrategy;
import com.gigiozzz.buffer.components.PersistenceStrategy;

class CircularRingBufferImpl<T extends Serializable> implements BufferManager<T> {

	private CircularRing<T> buffer = null;
	private int bufferMaxSize;
	private PersistenceStrategy<?> persistencer;
	private FlushStrategy flusher;
	private Runnable saver;

	@SuppressWarnings("unchecked")
	public CircularRingBufferImpl(int bufferMaxSize, PersistenceStrategy<?> persistencer, FlushStrategy flusher) {
		this.bufferMaxSize = bufferMaxSize;
		this.persistencer = persistencer;
		this.flusher = flusher;

		buffer = new CircularRing<>(bufferMaxSize);

		List<T> loaded = ((PersistenceStrategy<T>) persistencer).load();
		if (loaded != null && !loaded.isEmpty()) {
			loaded.stream().forEach(e -> buffer.add(e));
		}

		saver = () -> ((PersistenceStrategy<T>) this.persistencer).save(buffer.unmodifiableList());
	}


	@Override
	public void add(T val) throws BufferFullException {
		buffer.add(val);
		flusher.flushAfterBuf(saver);
	}

	@Override
	public Optional<T> get() {
		Optional<T> val = peek();
		remove();
		return val;
	}

	@Override
	public Optional<T> peek() {
		return buffer.peek();
	}

	@Override
	public void remove() {
		buffer.get();
		flusher.flushAfterDebuf(saver);
	}

	@Override
	public boolean isFull() {
		return buffer.size() == bufferMaxSize;
	}

	@Override
	public void flush() throws UnsupportedOperationException {
		flusher.flush(saver);
	}

	@Override
	public int size() {
		return buffer.size();
	}

	@Override
	public int capacity() {
		return bufferMaxSize;
	}

}
