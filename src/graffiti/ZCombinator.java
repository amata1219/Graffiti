package graffiti;

import java.util.function.Function;

public class ZCombinator {
	
	public static void main(String[] $){
		F<F<Integer>> g = f -> n -> n <= 1 ? n : f.apply(n - 1) + f.apply(n - 2);
		F<Integer> fib = z(g);
		System.out.println(fib.apply(1));
		
		F<F<Integer>> h = f -> n -> n <= 1 ? 1 : n * f.apply(n - 1);
		F<Integer> fact = z(h);
		System.out.println(fact.apply(30));
	}
	
	public static <T> F<T> z(F<F<T>> f){
		G<T> a = x -> f.apply(y -> x.apply(x).apply(y));
		G<T> b = x -> f.apply(y -> x.apply(x).apply(y));
		return a.apply(b);
	}
	
	public interface F<T> {
		
		T apply(T t);
		
	}
	
	public interface G<T> extends Function<G<T>, F<T>> { }
}
