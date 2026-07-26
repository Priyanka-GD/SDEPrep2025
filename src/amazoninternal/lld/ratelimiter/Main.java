package amazoninternal.lld.ratelimiter;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("==========================================");
        System.out.println("     RATE LIMITER TEST HARNESS           ");
        System.out.println("==========================================\n");

        testTokenBucket();
        Thread.sleep(1000);

        testLeakyBucket();
        Thread.sleep(1000);

        testSlidingWindowLog();
    }

    /**
     * 1. TOKEN BUCKET DEMO
     * Configured for capacity = 3 tokens, refilling 3 tokens every 1000ms.
     * Demonstrates bursting up to capacity and lazy refill after waiting.
     */
    private static void testTokenBucket() throws InterruptedException {
        System.out.println("--- [1] Testing Token Bucket Rate Limiter ---");
        // Capacity: 3, Refill: 3 tokens per 1000ms
        RateLimiter tokenBucket = new TokenBucketRateLimiter(3, 3, 1000);
        String clientKey = "user_1";

        System.out.println("Attempting 5 rapid requests (Capacity is 3)...");
        for (int i = 1; i <= 5; i++) {
            boolean allowed = tokenBucket.allowRequest(clientKey);
            System.out.printf("  Request %d: %s%n", i, allowed ? "ALLOWED (200 OK)" : "REJECTED (429 Too Many Requests)");
        }

        System.out.println("\nSleeping for 1000ms to allow tokens to refill...");
        Thread.sleep(1050);

        System.out.println("Attempting 2 new requests post-refill...");
        for (int i = 6; i <= 7; i++) {
            boolean allowed = tokenBucket.allowRequest(clientKey);
            System.out.printf("  Request %d: %s%n", i, allowed ? "ALLOWED (200 OK)" : "REJECTED (429 Too Many Requests)");
        }
        System.out.println();
    }

    /**
     * 2. LEAKY BUCKET DEMO
     * Configured with a capacity of 3 items in the queue.
     * Demonstrates rejection when the queue overflows.
     */
    private static void testLeakyBucket() {
        System.out.println("--- [2] Testing Leaky Bucket Rate Limiter ---");
        // Queue Capacity: 3
        RateLimiter leakyBucket = new LeakyBucketRateLimiter(3);
        String clientKey = "user_2";

        System.out.println("Filling the leaky bucket with 5 concurrent requests...");
        for (int i = 1; i <= 5; i++) {
            boolean allowed = leakyBucket.allowRequest(clientKey);
            System.out.printf("  Request %d: %s%n", i, allowed ? "ACCEPTED INTO BUCKET" : "OVERFLOW REJECTED");
        }
        System.out.println();
    }

    /**
     * 3. SLIDING WINDOW LOG DEMO
     * Configured for max 3 requests per 1000ms sliding window.
     * Demonstrates pruning old logs after the window slides.
     */
    private static void testSlidingWindowLog() throws InterruptedException {
        System.out.println("--- [3] Testing Sliding Window Log Rate Limiter ---");
        // Max 3 requests per 1000ms window
        RateLimiter slidingWindow = new SlidingWindowLogRateLimiter(3, 1000);
        String clientKey = "user_3";

        System.out.println("Executing 4 immediate requests (Limit is 3 per sec)...");
        for (int i = 1; i <= 4; i++) {
            boolean allowed = slidingWindow.allowRequest(clientKey);
            System.out.printf("  Request %d: %s%n", i, allowed ? "ALLOWED" : "RATE LIMITED");
        }

        System.out.println("\nWaiting 600ms (Window partially elapsed)...");
        Thread.sleep(600);

        System.out.println("Attempting request during middle of window...");
        System.out.printf("  Request 5: %s%n", slidingWindow.allowRequest(clientKey) ? "ALLOWED" : "RATE LIMITED (Window full)");

        System.out.println("\nWaiting another 500ms (Initial 4 requests have now expired from window)...");
        Thread.sleep(500);

        System.out.println("Attempting request after window expired...");
        System.out.printf("  Request 6: %s%n", slidingWindow.allowRequest(clientKey) ? "ALLOWED (Old logs pruned)" : "RATE LIMITED");
        System.out.println();
    }
}