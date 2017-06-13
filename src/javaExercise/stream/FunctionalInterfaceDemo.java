package javaExercise.stream;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class FunctionalInterfaceDemo {
	
	public static void main(String [] args) {
		doSomething(() -> "Hello World!", String::length, System.out::println);
	}
	
	private static void doSomething(
			Supplier<String> supplier, 
			Function<String, Integer> function, 
			Consumer<Integer> consumer) {
		consumer.accept(function.apply(supplier.get()));
	}

}
