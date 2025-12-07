package multithreading.future;

import java.util.concurrent.*;

public class FutureExample {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newFixedThreadPool(1);

        Callable<String> task = () -> {
            Thread.sleep(3000); // Simulate a long-running task
            return "Task completed";
        };

        Future<String> future = executor.submit(task);

        System.out.println("Task submitted, doing other stuff...");

        while (!future.isDone()) {
            System.out.println("Task is still running...");
            Thread.sleep(1000);
        }

        String result = future.get();
        System.out.println("Result: " + result);

        executor.shutdown();
    }
}