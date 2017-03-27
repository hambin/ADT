package java.stream;

import static java.util.stream.Collectors.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Stream;

public class CollectorDemo {
	
	public static void main(String [] args) {
		// 使用Collectors预定义的Collector
		Stream.of("a", "b", "c").collect(toList());
		Stream.of("a", "b", "c").collect(toCollection(LinkedList::new));
		
		// 串联Collector
		Object o1 = Stream.of("a", "b", "c").collect(
				groupingBy(String::length, 
						mapping(String::toUpperCase, 
								toList())));
		
		// 自定义Collector
		Object o2 = Stream.of("a", "b", "c").collect(new CollectorDemo().new UserDefinedCollector());
		
		System.out.println(o1.equals(o2));
	}
	
	// 自定义Collector
	class UserDefinedCollector implements 
		Collector<String, Map<Integer, List<String>>, Map<Integer, List<String>>> {

		@Override // 创建累加器
		public Supplier<Map<Integer, List<String>>> supplier() {
			return () -> new HashMap<>();
		}

		@Override // 将一个元素放入累加器
		public BiConsumer<Map<Integer, List<String>>, String> accumulator() {
			return (map, s) -> {
				Integer key = s.length();
				map.putIfAbsent(key, new ArrayList<>());
				map.get(key).add(s.toUpperCase());
			};
		}

		@Override // 合并两个累加器
		public BinaryOperator<Map<Integer, List<String>>> combiner() {
			return (a, b) -> {
				a.entrySet().addAll(b.entrySet());
				return a;
			};
		}

		@Override // 将累加器转换为最终输出类型
		public Function<Map<Integer, List<String>>, Map<Integer, List<String>>> finisher() {
			return Function.identity();
		}

		@Override // 定义一些特征
		public Set<Characteristics> characteristics() {
			return Collections.emptySet();
		}
		
	}

}
