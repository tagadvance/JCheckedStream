package com.tagadvance.checked;

@FunctionalInterface
public interface UncaughtExceptionHandler {

	void uncaughtException(Throwable t);

}
