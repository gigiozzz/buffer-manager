package com.gigiozzz.buffer.internal.circular_ring;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gigiozzz.buffer.tests.utils.TestObj;

class CirculaRingTest {
	private static final Logger logger = LoggerFactory.getLogger(CirculaRingTest.class);

	@Test
	void testCapacity() {
		CircularRing<TestObj> cr = new CircularRing<>(2);

		assertEquals(2, cr.capacity());

	}

	@Test
	void testSize() {
		CircularRing<TestObj> cr = new CircularRing<>(2);
		assertEquals(0, cr.size());
		cr.add(new TestObj("test1", 1));
		assertEquals(1, cr.size());
		cr.add(new TestObj("test2", 2));
		assertEquals(2, cr.size());
		cr.add(new TestObj("test3", 3));
		assertEquals(2, cr.size());
		cr.get();
		assertEquals(1, cr.size());
	}

	@Test
	void testGet() {
		CircularRing<TestObj> cr = new CircularRing<>(2);
		assertEquals(0, cr.unmodifiableList().size());
		TestObj to1 = new TestObj("test1", 1);
		cr.add(to1);
		TestObj t = cr.get().get();
		assertEquals(to1, t);

		assertEquals(Optional.empty(), cr.get());

		TestObj to2 = new TestObj("test2", 2);
		cr.add(to2);
		TestObj to3 = new TestObj("test3", 3);
		cr.add(to3);
		t = cr.get().get();
		assertEquals(to2, t);
	}

	@Test
	void testIsEmpty() {
		CircularRing<TestObj> cr = new CircularRing<>(2);
		assertTrue(cr.isEmpty());

		TestObj to1 = new TestObj("test1", 1);
		cr.add(to1);
		assertFalse(cr.isEmpty());
	}

	@Test
	void testPeek() {
		CircularRing<TestObj> cr = new CircularRing<>(3);
		assertEquals(Optional.empty(), cr.peek());

		TestObj to1 = new TestObj("test1", 1);
		cr.add(to1);
		TestObj t = cr.peek().get();
		assertEquals(to1, t);
		assertEquals(1, cr.size());

		t = cr.peek().get();
		assertEquals(to1, t);

		TestObj to2 = new TestObj("test2", 2);
		cr.add(to2);
		TestObj to3 = new TestObj("test3", 3);
		cr.add(to3);
		t = cr.peek().get();
		logger.debug("t:'{}'", t);
		assertEquals(to1, t);
		t = cr.peek().get();
		logger.debug("t:'{}'", t);
		t = cr.peek().get();
		logger.debug("t:'{}'", t);
		assertEquals(3, cr.size());

		assertEquals(3, cr.unmodifiableList().size());
	}

}
