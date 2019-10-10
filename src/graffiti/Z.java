package graffiti;

import java.util.function.Function;

public interface Z<I, O> extends Function<Z<I, O>, Function<I, O>> {
	
	public static void main(String[] $){
		Function<Integer, Integer> factorial = of(f -> n -> n <= 1 ? 1 : n * f.apply(n - 1));
		System.out.println(factorial.apply(10));
	}
	
	public static <I, O> Function<I, O> of(Function<Function<I, O>, Function<I, O>> f){
		Z<I, O> a = x -> f.apply(y -> x.apply(x).apply(y));
		Z<I, O> b = x -> f.apply(y -> x.apply(x).apply(y));
		return a.apply(b);
	}
	
}
