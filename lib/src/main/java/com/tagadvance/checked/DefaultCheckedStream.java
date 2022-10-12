package com.tagadvance.checked;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static java.util.Objects.requireNonNull;

public class DefaultCheckedStream<E> implements CheckedStream<E> {

	private final Stream<E> delegatee;
	private final List<FilteredUncaughtExceptionHandler> handlers;

	public DefaultCheckedStream(final Stream<E> delegatee) {
		this(delegatee, new ArrayList<>());
	}

	DefaultCheckedStream(final Stream<E> delegatee, final List<FilteredUncaughtExceptionHandler> handlers) {
		this.delegatee = requireNonNull(delegatee, "delegatee must not be null");
		this.handlers = handlers;
	}

	@Override
	public Stream<E> stream() {
		return delegatee;
	}

	@Override
	public <R> CheckedStream<R> fromStream(final Stream<R> stream) {
		if (delegatee.equals(stream)) {
			return (CheckedStream<R>) this;
		} else if (stream instanceof CheckedStream) {
			return (CheckedStream<R>) stream;
		}

		return new DefaultCheckedStream<>(stream, handlers);
	}

	@Override
	public CheckedStream<E> catchThrowable(final FilteredUncaughtExceptionHandler handler) {
		handlers.add(handler);

		return this;
	}

	@Override
	public Collection<FilteredUncaughtExceptionHandler> getUncaughtExceptionHandlers() {
		return Collections.unmodifiableCollection(handlers);
	}

}
