package com.tagadvance.checked;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

interface CheckedStream<V> extends Stream<V> {

	@Override
	default boolean isParallel() {
		return stream().isParallel();
	}

	default CheckedStream<V> filter(
			final CheckedPredicate<? super V> predicate) {
		return fromStream(stream().filter(predicate));
	}

	@Override
	default CheckedStream<V> filter(final Predicate<? super V> predicate) {
		return fromStream(stream().filter(predicate));
	}

	default <R> CheckedStream<R> map(
			final CheckedFunction<? super V, ? extends R> mapper) {
		return fromStream(stream().map(mapper));
	}

	@Override
	default <R> CheckedStream<R> map(
			final Function<? super V, ? extends R> mapper) {
		return fromStream(stream().map(mapper));
	}

	// FIXME: UncheckedIntStream
	@Override
	default IntStream mapToInt(final ToIntFunction<? super V> mapper) {
		return stream().mapToInt(mapper);
	}

	// FIXME: UncheckedLongStream
	@Override
	default LongStream mapToLong(final ToLongFunction<? super V> mapper) {
		return stream().mapToLong(mapper);
	}

	// FIXME: UncheckedDoubleStream
	@Override
	default DoubleStream mapToDouble(final ToDoubleFunction<? super V> mapper) {
		return stream().mapToDouble(mapper);
	}

	@Override
	default <R> CheckedStream<R> flatMap(
			final Function<? super V, ? extends Stream<? extends R>> mapper) {
		return fromStream(stream().flatMap(mapper));
	}

	// FIXME: UncheckedIntStream
	@Override
	default IntStream flatMapToInt(
			final Function<? super V, ? extends IntStream> mapper) {
		return stream().flatMapToInt(mapper);
	}

	// FIXME: UncheckedLongStream
	@Override
	default LongStream flatMapToLong(
			final Function<? super V, ? extends LongStream> mapper) {
		return stream().flatMapToLong(mapper);
	}

	// FIXME: UncheckedDoubleStream
	@Override
	default DoubleStream flatMapToDouble(
			final Function<? super V, ? extends DoubleStream> mapper) {
		return stream().flatMapToDouble(mapper);
	}

	@Override
	default CheckedStream<V> distinct() {
		return fromStream(stream().distinct());
	}

	@Override
	default CheckedStream<V> sorted() {
		return fromStream(stream().sorted());
	}

	default CheckedStream<V> sorted(
			final CheckedComparator<? super V> comparator) {
		return fromStream(stream().sorted(comparator));
	}

	@Override
	default CheckedStream<V> sorted(final Comparator<? super V> comparator) {
		return fromStream(stream().sorted(comparator));
	}

	default CheckedStream<V> peek(final CheckedConsumer<? super V> action) {
		return fromStream(stream().peek(action));
	}

	@Override
	default CheckedStream<V> peek(final Consumer<? super V> action) {
		return fromStream(stream().peek(action));
	}

	@Override
	default CheckedStream<V> limit(final long maxSize) {
		return fromStream(stream().limit(maxSize));
	}

	@Override
	default CheckedStream<V> skip(final long n) {
		return fromStream(stream().skip(n));
	}

	default void forEach(final CheckedConsumer<? super V> action) {
		terminalOperation(() -> stream().forEach(action));
	}

	@Override
	default void forEach(final Consumer<? super V> action) {
		terminalOperation(() -> stream().forEach(action));
	}

	default void forEachOrdered(final CheckedConsumer<? super V> action) {
		terminalOperation(() -> stream().forEachOrdered(action));
	}

	@Override
	default void forEachOrdered(final Consumer<? super V> action) {
		terminalOperation(() -> stream().forEachOrdered(action));
	}

	@Override
	default Object[] toArray() {
		return terminalOperation(() -> stream().toArray());
	}

	@Override
	default <A> A[] toArray(final IntFunction<A[]> generator) {
		return terminalOperation(() -> stream().toArray(generator));
	}

	@Override
	default V reduce(final V identity, final BinaryOperator<V> accumulator) {
		return terminalOperation(() -> stream().reduce(identity, accumulator));
	}

	@Override
	default Optional<V> reduce(final BinaryOperator<V> accumulator) {
		return terminalOperation(() -> stream().reduce(accumulator));
	}

	@Override
	default <U> U reduce(final U identity,
						 final BiFunction<U, ? super V, U> accumulator,
						 final BinaryOperator<U> combiner) {
		return terminalOperation(
				() -> stream().reduce(identity, accumulator, combiner));
	}

	@Override
	default <R> R collect(final Supplier<R> supplier,
						  final BiConsumer<R, ? super V> accumulator,
						  final BiConsumer<R, R> combiner) {
		return terminalOperation(
				() -> stream().collect(supplier, accumulator, combiner));
	}

	@Override
	default <R, A> R collect(final Collector<? super V, A, R> collector) {
		return terminalOperation(() -> stream().collect(collector));
	}

	default Optional<V> min(final CheckedComparator<? super V> comparator) {
		return terminalOperation(() -> stream().min(comparator));
	}

	@Override
	default Optional<V> min(final Comparator<? super V> comparator) {
		return terminalOperation(() -> stream().min(comparator));
	}

	default Optional<V> max(final CheckedComparator<? super V> comparator) {
		return terminalOperation(() -> stream().max(comparator));
	}

	@Override
	default Optional<V> max(final Comparator<? super V> comparator) {
		return terminalOperation(() -> stream().max(comparator));
	}

	@Override
	default long count() {
		return terminalOperation(stream()::count);
	}

	default boolean anyMatch(final CheckedPredicate<? super V> predicate) {
		return terminalOperation(() -> stream().anyMatch(predicate));
	}

	@Override
	default boolean anyMatch(final Predicate<? super V> predicate) {
		return terminalOperation(() -> stream().anyMatch(predicate));
	}

	default boolean allMatch(final CheckedPredicate<? super V> predicate) {
		return terminalOperation(() -> stream().allMatch(predicate));
	}

	@Override
	default boolean allMatch(final Predicate<? super V> predicate) {
		return terminalOperation(() -> stream().allMatch(predicate));
	}

	default boolean noneMatch(final CheckedPredicate<? super V> predicate) {
		return terminalOperation(() -> stream().noneMatch(predicate));
	}

	@Override
	default boolean noneMatch(final Predicate<? super V> predicate) {
		return terminalOperation(() -> stream().noneMatch(predicate));
	}

	@Override
	default Optional<V> findFirst() {
		return terminalOperation(stream()::findFirst);
	}

	@Override
	default Optional<V> findAny() {
		return terminalOperation(stream()::findAny);
	}

	@Override
	default Iterator<V> iterator() {
		return terminalOperation(stream()::iterator);
	}

	@Override
	default Spliterator<V> spliterator() {
		return terminalOperation(stream()::spliterator);
	}

	@Override
	default CheckedStream<V> sequential() {
		return fromStream(stream().sequential());
	}

	@Override
	default CheckedStream<V> parallel() {
		return fromStream(stream().parallel());
	}

	@Override
	default CheckedStream<V> unordered() {
		return fromStream(stream().unordered());
	}

	@Override
	default CheckedStream<V> onClose(final Runnable closeHandler) {
		stream().onClose(closeHandler);

		return this;
	}

	@Override
	default void close() {
		terminalOperation(stream()::close);
	}

	Stream<V> stream();

	<R> CheckedStream<R> fromStream(Stream<R> stream);

	default void consumeAll() {
		catchAll(e -> {
		});
	}

	default void logAll() {
		catchAll(e -> logger().error(e.getMessage(), e));
	}

	default CheckedStream<V> catchAll(UncaughtExceptionHandler handler) {
		return catchThrowable(e -> true, handler);
	}

	default CheckedStream<V> catchChecked(UncaughtExceptionHandler handler) {
		return catchThrowable(e -> e instanceof Exception, handler);
	}

	default CheckedStream<V> catchUnchecked(UncaughtExceptionHandler handler) {
		return catchThrowable(e -> e instanceof RuntimeException, handler);
	}

	default CheckedStream<V> catchThrowable(
			Predicate<? extends Throwable> predicate,
			UncaughtExceptionHandler handler) {
		return catchThrowable(new FilteredUncaughtExceptionHandler(predicate, handler));
	}

	CheckedStream<V> catchThrowable(FilteredUncaughtExceptionHandler handler);

	default <T extends Throwable> void terminalOperation(
			final Runnable runnable) {
		try {
			runnable.run();
		} catch (final RuntimeException e) {
			process(e);
		}
	}

	default <E, T extends Throwable> E terminalOperation(
			final Supplier<E> supplier) {
		try {
			return supplier.get();
		} catch (final RuntimeException e) {
			process(e);

			throw e;
		}
	}

	default <T extends Throwable> void process(final RuntimeException e) {
		final Throwable cause = e.getCause();
		getUncaughtExceptionHandlers().stream()
				.filter(handler -> handler.filter.test(cause))
				.map(handler -> handler.handler).findFirst()
				.ifPresentOrElse(handler -> handler.uncaughtException(cause), () -> {
					throw e;
				});
	}

	Collection<FilteredUncaughtExceptionHandler> getUncaughtExceptionHandlers();

	default Logger logger() {
		return LoggerFactory.getLogger(getClass());
	}

}
