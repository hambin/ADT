package javaExercise.lang;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

public class DefaultMethodDemo {
	
	public static void main(String [] args) {
		// default method为接口增加新方法，同时兼容以前的实现类
		new DefaultMethodDemo().new LegacyClass().acceptDouble(1D);
		new DefaultMethodDemo().new ModernClass().acceptDouble(1D);
		
		// 已有接口通过default method提供utility，并接受lambda表达式
		List<String> list = Arrays.asList("a", "ab", "abc", "b");
		Collections.sort(list, 
				Comparator.comparing(String::length).reversed().thenComparing(Function.identity()));
		System.out.println(list);
	}
	
	interface DefaultMethodInterface {
		String acceptInt(int i);
		default String acceptDouble(double d) {
			return String.valueOf(d);
		}
	}
	
	class LegacyClass implements DefaultMethodInterface {

		@Override public String acceptInt(int i) {
			return String.valueOf(i);
		}
		
	}
	
	class ModernClass implements DefaultMethodInterface {

		@Override public String acceptInt(int i) {
			return String.valueOf(i * 2);
		}
		
		@Override public String acceptDouble(double d) {
			return String.valueOf(d * 2);
		}
		
	}

}
