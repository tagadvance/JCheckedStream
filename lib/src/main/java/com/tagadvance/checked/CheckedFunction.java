package com.tagadvance.checked;

import java.util.function.Function;

@FunctionalInterface
public interface CheckedFunction<T, R> extends Function<T, R> {

	R applyChecked(T t) throws Exception;

	@Override
	default R apply(final T t) {
		try {
			return applyChecked(t);
		} catch (final Exception e) {
			throw new RuntimeException(e);
		}
	}

}
