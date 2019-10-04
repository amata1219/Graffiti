package graffiti;

import java.util.function.Function;

public class Tuple<T1, T2> {
	
	public final T1 t1;
	public final T2 t2;
	
	public static <T1, T2> Tuple<T1, T2> of(T1 t1, T2 t2){
		return new Tuple<>(t1, t2);
	}
	
	private Tuple(T1 t1, T2 t2){
		this.t1 = t1;
		this.t2 = t2;
	}
	
	public <T3, T4> Tuple<T3, T4> map(Function<T1, T3> mapper1, Function<T2, T4> mapper2){
		return of(mapper1.apply(t1), mapper2.apply(t2));
	}
	
	public <T3> Ex<T3> ex(T3 t3){
		return new Ex<>(t3);
	}
	
	private class Ex<T3> extends Tuple<Tuple<T1, T2>, T3> {
		
		private Ex(T3 t3){
			super(Tuple.this, t3);
		}
		
	}

}
