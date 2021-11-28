package com.gigiozzz.buffer.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.gigiozzz.buffer.BufferFullException;
import com.gigiozzz.buffer.BufferManager;
import com.gigiozzz.buffer.components.BufferBuilder;
import com.gigiozzz.buffer.internal.array_list.ArrayListBufferFactory;
import com.gigiozzz.buffer.tests.utils.TestObj;

class BufferManagerTest {

	@Test
	void testBufAndDebuf() throws BufferFullException {
		BufferManager<TestObj> buffer = BufferBuilder.get(TestObj.class)
				.build(ArrayListBufferFactory.getInstance(TestObj.class));

		TestObj to1 = new TestObj("test1", 1);
		TestObj to2 = new TestObj("test2", 2);

		buffer.add(to1);
		buffer.add(to2);

		assertEquals(2, buffer.size());

		assertEquals(to1, buffer.get().get());

		assertEquals(1, buffer.size());

	}

	@Test
	void testGetAndRemove() throws BufferFullException {
		BufferManager<TestObj> buffer = BufferBuilder.get(TestObj.class)
				.build(ArrayListBufferFactory.getInstance(TestObj.class));

		TestObj to1 = new TestObj("test1", 1);
		TestObj to2 = new TestObj("test2", 2);

		buffer.add(to1);
		buffer.add(to2);

		assertEquals(2, buffer.size());

		assertEquals(to1, buffer.peek().get());
		assertEquals(2, buffer.size());

		buffer.remove();

		assertEquals(to2, buffer.peek().get());
		assertEquals(1, buffer.size());

	}

	@Test
	void testEmpty() {
		BufferManager<TestObj> buffer = BufferBuilder.get(TestObj.class)
				.build(ArrayListBufferFactory.getInstance(TestObj.class));

		buffer.remove();

		assertEquals(Optional.empty(), buffer.peek());
		assertEquals(Optional.empty(), buffer.get());

	}

	@Test
	void testFull() {
		BufferManager<TestObj> buffer = BufferBuilder.get(TestObj.class).withCapacity(2)
				.build(ArrayListBufferFactory.getInstance(TestObj.class));

		TestObj to1 = new TestObj("test1", 1);
		TestObj to2 = new TestObj("test2", 2);
		
		try {
			buffer.add(to1);
			buffer.add(to2);
		} catch (Exception ex) {
			System.out.println("Error insert buffer: " + ex.getMessage());
		}
		
		assertTrue(buffer.isFull());
		assertThrows(BufferFullException.class, () -> buffer.add(to2));

	}

}
