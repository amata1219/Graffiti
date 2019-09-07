package graffiti;

import java.util.function.Function;

public class Option<T> {

	private static final Option<?> EMPTY = new Option<>(null);

	@SuppressWarnings("unchecked")
	public static <T> Option<T> empty(){
		return (Option<T>) EMPTY;
	}

	private final T value;

	private Option(T value){
		this.value = value;
	}

	public Option<T> unit(T value){
		return new Option<>(value);
	}

	public <U> Option<U> map(Function<T, ? extends U> mapper) {
		return isEmpty() ? new Option<>(mapper.apply(value)) : empty();
	}

	public <U> Option<U> flatMap(Function<T, Option<U>> mapper){
		return isEmpty() ? mapper.apply(value) : empty();
	}

	public boolean isEmpty(){
		return value == null;
	}

}
