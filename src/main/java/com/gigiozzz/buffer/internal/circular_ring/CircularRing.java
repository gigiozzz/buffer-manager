package com.gigiozzz.buffer.internal.circular_ring;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

class CircularRing<E> {

	// Head node
	Node<E> head;

	// Tail Node
	Node<E> tail;
	int size = 0;
	int capacity = 0;

	// Constructor
	public CircularRing(int capacity) {
		this.capacity = capacity;
	}

	// Addition of Elements
	public void add(E element) {

		// Size of buffer increases as elements
		// are added to the Linked List
		size++;

		// Checking if the buffer is full
		if (size > capacity) {
			head = head.next;
			size--;
		}

		// Checking if the buffer is empty
		if (head == null) {
			head = new Node<>(element);
			tail = head;
			return;
		}

		// Node element to be linked
		Node<E> temp = new Node<>(element);

		// Referencing the last element to the head node
		temp.next = head;

		// Updating the tail reference to the
		// latest node added
		tail.next = temp;

		// Updating the tail to the latest node added
		tail = temp;
	}

	// Retrieving the head element
	public Optional<E> get() {

		// Checking if the buffer is empty
		if (size == 0) {
			return Optional.empty();
		}
		// Getting the element
		E element = head.data;

		// Updating the head pointer
		head = head.next;

		// Updating the tail reference to
		// the new head pointer
		tail.next = head;

		// Decrementing the size
		size--;
		if (size == 0) {
			// Removing any references present
			// when the buffer becomes empty
			head = tail = null;
		}
		return Optional.of(element);
	}

	// Retrieving the head element without deleting
	public Optional<E> peek() {

		// Checking if the buffer is empty
		if (size == 0) {
			return Optional.empty();
		}
		// Getting the element
		E element = head.data;
		return Optional.of(element);
	}

	// Checking if the buffer is empty
	public boolean isEmpty() {
		return size == 0;
	}

	// Retrieving the size of the buffer
	public int size() {
		return size;
	}

	public int capacity() {
		return capacity;
	}

	public List<E> unmodifiableList() {
		List<E> list = new ArrayList<>();

		Node<E> cursor = head;
		do {
			if (cursor != null) {
				list.add(cursor.data);
				cursor = cursor.next;
			}
		} while (cursor != head);

		return Collections.unmodifiableList(list);
	}
}
