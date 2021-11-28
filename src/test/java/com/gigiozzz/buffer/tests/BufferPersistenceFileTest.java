package com.gigiozzz.buffer.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.gigiozzz.buffer.components.PersistenceStrategy;
import com.gigiozzz.buffer.strategy.PersistenceStrategyFileJsonImpl;
import com.gigiozzz.buffer.tests.utils.TestObj;

class BufferPersistenceFileTest {

	private List<TestObj> listTest = Arrays.asList(new TestObj[] { new TestObj("obj1", 1), new TestObj("obj2", 2), });

	@Test
	void testCapacity() throws IOException {
		PersistenceStrategy<TestObj> ps = new PersistenceStrategyFileJsonImpl<>(Paths.get("temp.file"), TestObj.class);
		ps.save(listTest);

		List<TestObj> l = ps.load();
		assertEquals(2, l.size());
	}


}
