package graffiti;

import java.util.Arrays;
import java.util.Collection;
import java.util.function.Function;

import com.google.common.base.Joiner;

public class ArgList {

	private final String[] args;
	private int index;

	public ArgList(String[] args){
		this.args = args;
	}

	public int length(){
		return args.length;
	}

	public Maybe<String> next(){
		return next(s -> s);
	}

	public <T> Maybe<T> range(int startInclusive, int endExclusive, Function<Collection<String>, Maybe<T>> action){
		String[] range = Arrays.copyOfRange(args, limit(startInclusive), limit(endExclusive));
		return action.apply(Arrays.asList(range));
	}

	public Maybe<String> join(int startInclusive, int endExclusive){
		return range(startInclusive, endExclusive, range -> {
			String result = Joiner.on(' ').join(range);
			return Maybe.unit(result.isEmpty() ? null : result);
		});
	}

	public Maybe<Boolean> nextBoolean(){
		return next(Boolean::valueOf);
	}

	public Maybe<Character> nextChar(){
		return next(s -> s.length() == 1 ? s.charAt(0) : null);
	}

	public Maybe<Byte> nextByte(){
		return next(Byte::valueOf);
	}

	public Maybe<Short> nextShort(){
		return next(Short::valueOf);
	}

	public Maybe<Integer> nextInt(){
		return next(Integer::valueOf);
	}

	public Maybe<Long> nextLong(){
		return next(Long::valueOf);
	}

	public Maybe<Float> nextFloat(){
		return next(Float::valueOf);
	}

	public Maybe<Double> nextDouble(){
		return next(Double::valueOf);
	}

	private <T> Maybe<T> next(Function<String, T> mapper){
		T value = null;
		try{
			value = mapper.apply(index < args.length ? args[index++] : null);
		}catch(Exception e){

		}
		return Maybe.unit(value);
	}

	private int limit(int index){
		return Math.max(Math.min(index, args.length), 0);
	}

}
