package com.tagadvance.checked;

import java.util.function.Consumer;

@FunctionalInterface
public interface CheckedConsumer<T> extends Consumer<T> {

	void acceptChecked(T t) throws Exception;

	@Override
	default void accept(T t) {
		try {
			acceptChecked(t);
		} catch (final Exception e) {
			throw new RuntimeException(e);
		}
	}

}
