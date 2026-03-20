package lowleveldesign;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class LeakyBucket {
    // The bucket (buffer) with a fixed capacity
    private final LinkedBlockingQueue<Integer> bucket;
    private final int capacity;

    public LeakyBucket(int capacity, int leakRatePerSecond) {
        this.capacity = capacity;
        this.bucket = new LinkedBlockingQueue<>(capacity);

        // This represents the "Leak" - a steady drip out of the bottom
        ScheduledExecutorService leakScheduler = Executors.newSingleThreadScheduledExecutor();

        long delayInMs = 1000 / leakRatePerSecond;

        leakScheduler.scheduleAtFixedRate(() -> {
            Integer request = bucket.poll();
            if (request != null) {
                System.out.println("Processing Request ID: " + request + " | Bucket size: " + bucket.size());
            } else {
                System.out.println("Bucket empty. Shutting down leak...");
                leakScheduler.shutdown();
            }
        }, 1000, delayInMs, TimeUnit.MILLISECONDS);
    }

    public boolean addRequest(int requestId) {
        // Try to add to the bucket. If full, it "overflows" (returns false)
        if (bucket.offer(requestId)) {
            System.out.println("Request " + requestId + " added to bucket.");
            return true;
        } else {
            System.out.println("Request " + requestId + " discarded! Bucket is FULL.");
            return false;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // Capacity of 5, Leaks 1 request every 1 second
        LeakyBucket leakyBucket = new LeakyBucket(5, 1);

        // Simulate a BURST of 10 requests arriving at once
        System.out.println("--- Sudden burst of 10 requests ---");
        for (int i = 1; i <= 10; i++) {
            leakyBucket.addRequest(i);
        }
    }
}

/*
 * Using LinkedBlockingQueue handles concurrency between the main and leakScheduler thread.
 * By calling shutdown and poll, the program doesn't hang forever after finishing work.
 * Need to keep delay to 100ms or 1000 ms because if computer is slow for split second and
 * leakScheduler wakes up before leakyBucket.addRequest(i) even finishes, it will see an
 * empty bucket and shut down the program.
 * */