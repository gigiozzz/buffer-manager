package com.gigiozzz.buffer;

/**
 * A BufferFullException is thrown when an application tries to buffer a new
 * element with a <code>BufferManager</code> but the buffer is full otherwise
 * the butter size is equal to the buffer maximum capacity.
 * 
 */
public class BufferFullException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructs an <code>BufferFullException</code> without a detail message.
	 */
	public BufferFullException() {
		super();
	}

	/**
	 * Constructs an <code>BufferFullException</code> with a detail message.
	 *
	 * @param s the detail message.
	 */
	public BufferFullException(String s) {
		super(s);
	}
}
