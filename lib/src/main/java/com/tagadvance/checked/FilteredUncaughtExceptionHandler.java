package com.tagadvance.checked;

import java.util.function.Predicate;

import static java.util.Objects.requireNonNull;

final class FilteredUncaughtExceptionHandler {

	final Predicate filter;
	final UncaughtExceptionHandler handler;

	protected FilteredUncaughtExceptionHandler(
			final Predicate<? extends Throwable> predicate,
			final UncaughtExceptionHandler handler) {
		this.filter =
				requireNonNull(predicate, "predicate must not be null");
		this.handler = requireNonNull(handler, "handler must not be null");
	}

	public void handleUncaughtCheckedException(final Throwable t) {
		if (filter.test(t)) {
			handler.uncaughtException(t);
		}
	}

}
