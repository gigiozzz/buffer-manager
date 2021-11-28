package com.gigiozzz.buffer.components;

import java.io.Serializable;
import java.util.List;

public interface PersistenceStrategy<T extends Serializable> {

	public void save(List<T> list);

	public List<T> load();

}
