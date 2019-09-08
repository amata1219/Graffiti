package graffiti;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;

public interface Maybe<T> {

	public static void main(String[] $){
		Maybe<Integer> a = unit(1);
		Maybe<Integer> b = unit(null);
		Maybe<Integer> r = a.flat(x -> b.bind(y -> x + y));
		r.runBy(System.out::println);
	}

	public static <T> Maybe<T> unit(T value){
		return value != null ? new Just<>(value) : Nothing.empty();
	}

	<U> Maybe<U> bind(Function<T, U> mapper);

	<U> Maybe<U> flat(Function<T, Maybe<U>> mapper);

	void runBy(Consumer<T> action);

	public static class Just<T> implements Maybe<T> {

		private final T value;

		public Just(T value){
			this.value = Objects.requireNonNull(value);
		}

		@Override
		public <U> Maybe<U> bind(Function<T, U> mapper) {
			return new Just<>(mapper.apply(value));
		}

		@Override
		public <U> Maybe<U> flat(Function<T, Maybe<U>> mapper) {
			return mapper.apply(value);
		}

		@Override
		public void runBy(Consumer<T> action) {
			action.accept(value);
		}

	}

	public static class Nothing<T> implements Maybe<T> {

		private static final Nothing<?> EMPTY = new Nothing<>();

		@SuppressWarnings("unchecked")
		private static <T> Nothing<T> empty(){
			return (Nothing<T>) EMPTY;
		}

		@Override
		public <U> Maybe<U> bind(Function<T, U> mapper) {
			return empty();
		}

		@Override
		public <U> Maybe<U> flat(Function<T, Maybe<U>> mapper) {
			return empty();
		}

		@Override
		public void runBy(Consumer<T> action) {

		}

	}

}
