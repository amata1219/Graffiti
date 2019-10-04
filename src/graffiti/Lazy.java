package graffiti;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.function.Supplier;

public class Lazy<T> {
	
	private Supplier<T> supplier;
	private T value;
	
	public static <T> Lazy<T> of(Supplier<T> supplier){
		return new Lazy<>(supplier);
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T of(Class<T> type, Supplier<T> supplier){
		if(!type.isInterface()) throw new IllegalArgumentException("type has to be an interface");
		Lazy<T> lazy = of(supplier);
		InvocationHandler handler = (proxy, method, args) -> method.invoke(lazy.get(), args);
		return (T) Proxy.newProxyInstance(type.getClassLoader(), new Class<?>[]{type}, handler);
	}
	
	private Lazy(Supplier<T> supplier){
		this.supplier = supplier;
	}
	
	public T get(){
		if(supplier != null){
			value = supplier.get();
			supplier = null;
		}
		return value;
	}

}
