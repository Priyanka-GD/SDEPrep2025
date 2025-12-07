package multithreading.leetcode;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class MultiSyntax {
    public static void main(String[] args) {
        /*ExecutorService executorService1 = Executors.newFixedThreadPool(10);
        ExecutorService executorService2 = Executors.newSingleThreadExecutor();
        ExecutorService executorService3 = Executors.newCachedThreadPool();
        ExecutorService executorService4 = Executors.newScheduledThreadPool(10);

        for (int idx = 0; idx < 5; idx++) {
            executorService1.execute(() -> System.out.println(Thread.currentThread().getName() + " executing."));
        }
        executorService1.shutdown();

        CompletableFuture.supplyAsync(() -> "Task 1")
                .thenApply(result -> result + " -> Task 2")
                .thenAccept(System.out::println)
                .join(); // Ensures the main thread waits for completion
    */

    }

}
