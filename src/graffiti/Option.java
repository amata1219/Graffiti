package graffiti;

import java.util.function.Function;

public class Option<T> {

	private static final Option<?> EMPTY = new Option<>(null);

	public static void main(String[] $){
		Option<String> option1 = unit("A");
		Option<String> option2 = option1.flatMap(Option::unit);

		System.out.println(option1.toString());
		System.out.println(option2.toString());
		System.out.println(option1.toString().equals(option2.toString()));
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
		return isEmpty() ? new Option<>(mapper.apply(value)) : empty();
	}

	public <U> Option<U> flatMap(Function<T, Option<U>> mapper){
		return isEmpty() ? empty() : mapper.apply(value);
	}

	public boolean isEmpty(){
		return value == null;
	}

	@Override
	public String toString(){
		return isEmpty() ? "Option.empty" : "Option." + value.toString();
	}

}
