package com.tagadvance.checked;

import java.util.function.Predicate;

@FunctionalInterface
public interface CheckedPredicate<T> extends Predicate<T> {

	boolean testChecked(T t) throws Exception;

	default boolean test(T t) {
		try {
			return testChecked(t);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
