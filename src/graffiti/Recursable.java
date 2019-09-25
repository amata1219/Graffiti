package graffiti;

@FunctionalInterface
public interface Recursable<T, R> {

	public static void main(String[] args){
		Recursable<Integer, Integer> factorial = define((n, self) -> n <= 1 ? n : n * self.apply(n - 1));
		System.out.println(factorial.apply(1));
	}

	static <T, R> Recursable<T, R> define(Recursable<T, R> recursable){
		return recursable;
	}

	R apply(T t, Recursable<T, R> self);

	default R apply(T t){
		return apply(t, this);
	}

}
