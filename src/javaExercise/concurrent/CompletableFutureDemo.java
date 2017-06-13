package javaExercise.concurrent;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureDemo {

	public static void main(String [] args) {
		CompletableFuture<Void> cf = CompletableFuture.supplyAsync(() -> {
			try { Thread.sleep(1000); } catch (InterruptedException e) {}
			return "Hello".equals("World");
		}).thenApply(b -> b ? "Excellent" : "What a pity").thenAccept(System.out::println);
		System.out.println("Not block main thread!");
		cf.join();
		
		// 实现线程同步壁垒
		CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> {
			try { Thread.sleep(1000); } catch (InterruptedException e) {}
			return "Hello ";
		});
		CompletableFuture<String> cf2 = CompletableFuture.supplyAsync(() -> "World!");
		CompletableFuture<Void> cf3 = cf1.thenCombine(cf2, (s1, s2) -> s1 + s2).thenAccept(System.out::println);
		cf1.acceptEither(cf2, System.out::println);
		cf3.join();
	}
	
}
