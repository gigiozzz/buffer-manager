package com.gigiozzz.buffer.strategy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.gigiozzz.buffer.components.PersistenceStrategy;

public class PersistenceStartegyNullImpl implements PersistenceStrategy<Serializable> {

	@Override
	public void save(List<Serializable> list) {
		// do nothing
	}

	@Override
	public List<Serializable> load() {
		return new ArrayList<>();
	}

}
