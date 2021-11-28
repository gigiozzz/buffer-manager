package com.gigiozzz.buffer.internal.array_list;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.gigiozzz.buffer.BufferFullException;
import com.gigiozzz.buffer.BufferManager;
import com.gigiozzz.buffer.components.FlushStrategy;
import com.gigiozzz.buffer.components.PersistenceStrategy;

class ArrayListBufferImpl<T extends Serializable> implements BufferManager<T> {

	private List<T> buffer = null;
	private int bufferMaxSize;
	private PersistenceStrategy<T> persistencer;
	private FlushStrategy flusher;
	private Runnable saver;

	public ArrayListBufferImpl(int bufferMaxSize, PersistenceStrategy<T> persistencer, FlushStrategy flusher) {
		this.bufferMaxSize = bufferMaxSize;
		this.persistencer = persistencer;
		this.flusher = flusher;

		buffer = new ArrayList<>();

		List<T> loaded = persistencer.load();
		if (loaded != null && !loaded.isEmpty()) {
			buffer.addAll(loaded);
		}

		saver = () -> this.persistencer.save(buffer);
	}
	
	@Override
	public void add(T val) throws BufferFullException {
		if (isFull()) {
			throw new BufferFullException();
		} else {
			buffer.add(val);
		}
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
		if (!buffer.isEmpty()) {
			return Optional.ofNullable(buffer.get(0));
		} else {
			return Optional.empty();
		}
	}

	@Override
	public void remove() {
		if (!buffer.isEmpty()) {
			buffer.remove(0);
		}
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
