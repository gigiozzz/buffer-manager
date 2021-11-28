package com.gigiozzz.buffer.components;

public interface FlushStrategy {

	public void flush(Runnable save);

	public void flushAfterBuf(Runnable save);

	public void flushAfterDebuf(Runnable save);

}
