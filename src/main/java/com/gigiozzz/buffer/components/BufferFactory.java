package com.gigiozzz.buffer.components;

import java.io.Serializable;

import com.gigiozzz.buffer.BufferManager;

public interface BufferFactory<T extends Serializable> {

	public BufferManager<T> build(int bufferMaxSize, PersistenceStrategy<T> persistencer, FlushStrategy flusher);

}
