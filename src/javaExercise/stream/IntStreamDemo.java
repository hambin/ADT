package javaExercise.stream;

import java.util.IntSummaryStatistics;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class IntStreamDemo {
	
	public static void main(String [] args) {
		// 代替传统的for循环
		IntStream.range(0, 100).forEach(i -> {});
		
		// 检查数组
		IntStream.of(new int [] {1, 2, 3}).anyMatch(i -> i > 3);
		
		// 自定义产生数字逻辑
		IntStream.iterate(0, i -> i * 2).limit(10);
		IntStream.generate(() -> ThreadLocalRandom.current().nextInt(10)).limit(10);
		
		// 计算统计特征
		IntStream.rangeClosed(1, 100).sum();
		IntSummaryStatistics stat = IntStream.rangeClosed(1, 100).summaryStatistics();
		
		// 并行处理
		IntStream.range(0, 100).parallel().forEach(i -> {});
		
		// 和Stream的转换
		Stream<Integer> s1 = IntStream.range(0, 100).boxed();
		Stream<String> s2 = IntStream.range(0, 100).mapToObj(i -> String.valueOf(i));
		IntStream s3 = s2.mapToInt(s -> Integer.parseInt(s));
	}

}
