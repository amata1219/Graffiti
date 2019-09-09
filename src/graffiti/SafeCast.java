package graffiti;

public class SafeCast {

	public static <S, T extends S> T down(S value, Class<T> target){
		return cast(value, target);
	}

	public static <T, S extends T> T up(S value, Class<T> target){
		return cast(value, target);
	}

	private static <S, T> T cast(S value, Class<T> target){
		return target.isInstance(value) ? target.cast(value) : null;
	}

}
