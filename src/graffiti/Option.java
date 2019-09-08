package graffiti;

import java.util.function.Consumer;
import java.util.function.Function;

public class Option<T> {

	private static final Option<?> EMPTY = new Option<>(null);

	public static void main(String[] $){
		Option<Integer> a = unit(10);
		Option<Integer> b = unit(20);
		a.flatMap(x -> b.map(y -> x + y)).ifPresent(System.out::println);
	}

	@SuppressWarnings("unchecked")
	public static <T> Option<T> empty(){
		return (Option<T>) EMPTY;
	}

	public static <T> Option<T> unit(T value){
		return new Option<>(value);
	}

	private final T value;

	private Option(T value){
		this.value = value;
	}

	public <U> Option<U> map(Function<T, ? extends U> mapper) {
		return isEmpty() ? empty() : new Option<>(mapper.apply(value));
	}

	public <U> Option<U> flatMap(Function<T, Option<U>> mapper){
		return isEmpty() ? empty() : mapper.apply(value);
	}

	public void ifPresent(Consumer<T> action){
		if(!isEmpty()) action.accept(value);
	}

	public boolean isEmpty(){
		return value == null;
	}

	@Override
	public String toString(){
		return isEmpty() ? "Option.empty" : "Option." + value.toString();
	}

}
