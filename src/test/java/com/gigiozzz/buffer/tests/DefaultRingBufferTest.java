package com.gigiozzz.buffer.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gigiozzz.buffer.BufferFullException;
import com.gigiozzz.buffer.BufferManager;
import com.gigiozzz.buffer.DefaultRingBuffer;
import com.gigiozzz.buffer.tests.utils.TestObj;

@TestMethodOrder(MethodOrderer.MethodName.class)
class DefaultRingBufferTest {
	private static final Logger logger = LoggerFactory.getLogger(DefaultRingBufferTest.class);

	@BeforeEach
	void cleanAll() throws IOException {
		BufferManager<TestObj> buffer = DefaultRingBuffer.build(TestObj.class);
		// rimuovo tutti
		IntStream.range(0, buffer.size()).forEach(t -> {
			buffer.remove();
		});
		buffer.flush();
	}

	@Test
	void test1_CapacitySize() throws IOException {
		BufferManager<TestObj> buffer = DefaultRingBuffer.build(TestObj.class);

		assertEquals(BufferManager.DEFAULT_CAPACITY, buffer.capacity());
		assertEquals(0, buffer.size());
		assertFalse(buffer.isFull());

		List<TestObj> list = IntStream.range(1, 101).mapToObj(i -> new TestObj("test" + i, i))
				.collect(Collectors.toList());

		list.stream().forEach(t -> {
			try {
				buffer.add(t);
			} catch (BufferFullException ex) {
				logger.error("error buffering", ex);
			}
		});

		assertEquals(BufferManager.DEFAULT_CAPACITY, buffer.size());
		assertTrue(buffer.isFull());

	}

	@Test
	void test2_Flush100Elements() throws IOException {
		BufferManager<TestObj> buffer = DefaultRingBuffer.build(TestObj.class);

		List<TestObj> list = IntStream.range(1, 200).mapToObj(i -> new TestObj("test" + i, i))
				.collect(Collectors.toList());


		list.stream().forEach(t -> {
			try {
				buffer.add(t);
			} catch (BufferFullException ex) {
				logger.error("error buffering", ex);
			}
		});

		assertEquals(BufferManager.DEFAULT_CAPACITY, buffer.size());
		assertTrue(buffer.isFull());

		long start = System.currentTimeMillis();
		buffer.flush();
		logger.info("buffer size:'{}' flushtime:'{}' ms", buffer.size(), System.currentTimeMillis() - start);

		start = System.currentTimeMillis();
		BufferManager<TestObj> bufferLoad = DefaultRingBuffer.build(TestObj.class);
		logger.info("buffer size:'{}' flushtime:'{}' ms", bufferLoad.size(), System.currentTimeMillis() - start);
	}

	@Test
	void test3_Flush0Elements() throws IOException {
		BufferManager<TestObj> buffer = DefaultRingBuffer.build(TestObj.class);

		// assertEquals(BufferManager.DEFAULT_CAPACITY, buffer.getSize());
		assertFalse(buffer.isFull());
		assertEquals(0, buffer.size());

		long start = System.currentTimeMillis();
		buffer.flush();
		logger.info("buffer size:'{}' flushtime:'{}' ms", buffer.size(), System.currentTimeMillis() - start);

		start = System.currentTimeMillis();
		BufferManager<TestObj> bufferLoad = DefaultRingBuffer.build(TestObj.class);
		logger.info("buffer size:'{}' flushtime:'{}' ms", bufferLoad.size(), System.currentTimeMillis() - start);

	}
}
