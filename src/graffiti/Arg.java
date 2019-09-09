package graffiti;

import java.util.function.Function;

public class Arg {

	public static Boolean toBoolean(String s){
		return convert(s, Boolean::valueOf);
	}

	public static Character toChar(String s){
		return convert(s, t -> t.length() == 1 ? t.charAt(0) : null);
	}

	public static Byte toByte(String s){
		return convert(s, Byte::valueOf);
	}

	public static Short toShort(String s){
		return convert(s, Short::valueOf);
	}

	public static Integer toInt(String s){
		return convert(s, Integer::valueOf);
	}

	public static Long toLong(String s){
		return convert(s, Long::valueOf);
	}

	public static Float toFloat(String s){
		return convert(s, Float::valueOf);
	}

	public static Double toDouble(String s){
		return convert(s, Double::valueOf);
	}

	private static <T> T convert(String s, Function<String, T> converter){
		T value = null;
		try{
			value = converter.apply(s);
		}catch(Exception e){

		}
		return value;
	}

}
