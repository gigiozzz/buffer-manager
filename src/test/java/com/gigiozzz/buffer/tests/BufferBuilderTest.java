package com.gigiozzz.buffer.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.gigiozzz.buffer.BufferManager;
import com.gigiozzz.buffer.components.BufferBuilder;
import com.gigiozzz.buffer.internal.array_list.ArrayListBufferFactory;
import com.gigiozzz.buffer.tests.utils.BufferBuilderFlushStrategyTest;
import com.gigiozzz.buffer.tests.utils.BufferBuilderPersistenceStrategyTest;
import com.gigiozzz.buffer.tests.utils.BufferBuilderPersistenceStrategyTest.BufferBuilderInternalFlushStrategyTest;
import com.gigiozzz.buffer.tests.utils.TestObj;

class BufferBuilderTest {

	@Test
	void testCapacity() {
		BufferManager<TestObj> buffer = BufferBuilder.get(TestObj.class)
				.build(ArrayListBufferFactory.getInstance(TestObj.class));
		assertEquals(BufferManager.DEFAULT_CAPACITY, buffer.capacity());

		int capcity = 5;
		buffer = BufferBuilder.get(TestObj.class).withCapacity(capcity)
				.build(ArrayListBufferFactory.getInstance(TestObj.class));
		assertEquals(capcity, buffer.capacity());

	}

	@Test
	void testFlush() {
		BufferManager<TestObj> buffer = BufferBuilder.get(TestObj.class)
				.withFlushStrategy(new BufferBuilderFlushStrategyTest())
				.build(ArrayListBufferFactory.getInstance(TestObj.class));

		TestObj to1 = new TestObj("test1", 1);

		assertThrows(UnsupportedOperationException.class, () -> buffer.flush());
		assertThrows(UnsupportedOperationException.class, () -> buffer.add(to1));
		assertThrows(UnsupportedOperationException.class, () -> buffer.get());

	}

	@Test
	void testPersistence() {
		BufferManager<TestObj> buffer = BufferBuilder.get(TestObj.class)
				.withFlushStrategy(new BufferBuilderInternalFlushStrategyTest())
				.withPersistenceStrategy(new BufferBuilderPersistenceStrategyTest())
				.build(ArrayListBufferFactory.getInstance(TestObj.class));

		assertThrows(IllegalArgumentException.class, () -> buffer.get());

	}

}
