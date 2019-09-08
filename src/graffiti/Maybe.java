package graffiti;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;

public interface Maybe<T> {

	public static <T> Maybe<T> unit(T value){
		return value != null ? new Just<>(value) : Nothing.empty();
	}

	<U> Maybe<U> bind(Function<T, U> binder);

	<U> Maybe<U> flatBind(Function<T, Maybe<U>> binder);

	void pure(Consumer<T> action);

	public static class Just<T> implements Maybe<T> {

		private final T value;

		public Just(T value){
			this.value = Objects.requireNonNull(value);
		}

		@Override
		public <U> Maybe<U> bind(Function<T, U> binder) {
			return new Just<>(binder.apply(value));
		}

		@Override
		public <U> Maybe<U> flatBind(Function<T, Maybe<U>> binder) {
			return binder.apply(value);
		}

		@Override
		public void pure(Consumer<T> action) {
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
		public <U> Maybe<U> bind(Function<T, U> binder) {
			return empty();
		}

		@Override
		public <U> Maybe<U> flatBind(Function<T, Maybe<U>> binder) {
			return empty();
		}

		@Override
		public void pure(Consumer<T> action) {

		}

	}

}
