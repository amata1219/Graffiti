package graffiti;

import java.util.function.Function;

public interface Monad<T> {

	/* T - map
	 * η - unit
	 * μ - flat
	 */

	<U> Monad<U> map(T value, Function<T, U> mapper);

}
