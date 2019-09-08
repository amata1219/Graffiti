package graffiti;

import java.util.function.Function;

public class State<S, A> {

	public static <S, A> State<S, A> unit(A a){
		return new State<>(s -> new Tuple<>(a, s));
	}

	private final Function<S, Tuple<A, S>> runState;

	public State(Function<S, Tuple<A, S>> runState){
		this.runState = runState;
	}

	public <B> State<S, B> bind(Function<A, B> binder){
		return flatBind(a -> unit(binder.apply(a)));
	}

	public <B> State<S, B> flatBind(Function<A, State<S, B>> binder){
		return new State<>(s -> {
			Tuple<A, S> tuple = runState.apply(s);
			return binder.apply(tuple.first).runState.apply(tuple.second);
		});
	}

}
