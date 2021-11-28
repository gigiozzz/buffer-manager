package com.gigiozzz.buffer;

import java.io.Serializable;
import java.nio.file.Path;
import java.util.Optional;

/**
 * An ordered buffer system based on FIFO structure.
 * <p>
 * The user of this interface has precise control over:
 * <ul>
 * <li>peek or get element in the the buffer</li>
 * <li>the type of flush system with the use of <code>FlushStartegy</code>
 * <li>when flush the buffer with the use of <code>PersistenceStrategy</code>
 * </ul>
 * </p>
 */
public interface BufferManager<T extends Serializable> {

	/**
	 * Appends the specified element to the end of this buffer.
	 *
	 * @param e element to be appended to this buffer
	 * @throws BufferFullException if the buffer was already full
	 * 
	 */
	public void add(T e) throws BufferFullException;

	/**
	 * Returns the element from the first position in this buffer. This operation
	 * removes the element returned from the buffer.
	 *
	 * @return the element from the first position in this buffer wrapped in an
	 *         {@link Optional}
	 */
	public Optional<T> get();

	/**
	 * Returns the element from the first position in this buffer. This operation
	 * doesn't remove the element returned from the buffer.
	 *
	 * @return the element from the first position in this buffer wrapped in an
	 *         {@link Optional}
	 */
	public Optional<T> peek();

	/**
	 * Removes the first occurrence from this buffer, if it is present. If this
	 * buffer does not contain elements, it is unchanged.
	 *
	 */
	public void remove();

	/**
	 * Flushes the buffer.
	 * 
	 * <p>
	 * One flush() invocation will copy all buffer's elements into a list saved with
	 * the underlying implementation of <code>PersistenceyStrategy<code>. Flushing
	 * occurs according to the implementation of <code>FlushStrategy<code>.
	 * </p>
	 */
	public void flush();

	/**
	 * Returns {@code true} if this buffer has reached its maximum capacity.
	 *
	 * @return {@code true} if this list contains the maximum number of elements
	 */
	public boolean isFull();

	/**
	 * Returns the number of elements in this buffer.
	 *
	 * @return the number of elements in this buffer
	 */
	public int size();

	/**
	 * Returns the number of the maximum size of elements this buffer can allocate.
	 *
	 * @return the number of the maximum size of elements this buffer can allocate
	 */
	public int capacity();

	/**
	 * The default maximum size of elements to allocate.
	 */
	public static final int DEFAULT_CAPACITY = 100;

	/**
	 * The default path size to use to save with flush the elements of the buffer.
	 */
	public static final Path DEFAULT_PATH = Path.of("temp.data");

}
