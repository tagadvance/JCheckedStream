package com.tagadvance.checked;

import java.util.Comparator;

@FunctionalInterface
public interface CheckedComparator<T> extends Comparator<T> {

	int compareChecked(T t1, T t2) throws Exception;

	@Override
	default int compare(final T t1, T t2) {
		try {
			return compareChecked(t1, t2);
		} catch (final Exception e) {
			throw new RuntimeException(e);
		}
	}

}
