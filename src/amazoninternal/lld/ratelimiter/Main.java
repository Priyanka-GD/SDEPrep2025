package amazoninternal.lld.ratelimiter;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("==========================================");
        System.out.println("     RATE LIMITER DEMONSTRATION           ");
        System.out.println("==========================================\n");

        testTokenBucket();
        Thread.sleep(1000);

        testLeakyBucket();
        Thread.sleep(1000);

        testSlidingWindowLog();
    }

    private static void testTokenBucket() throws InterruptedException {
        System.out.println("--- [1] Testing Token Bucket Rate Limiter (Via Factory) ---");
        // Capacity: 3, Window: 1000ms, Refill: 3 tokens per window
        RateLimiter tokenBucket = RateLimiterFactory.createRateLimiter(
                RateLimiterFactory.Algorithm.TOKEN_BUCKET, 3, 1000, 3
        );
        String clientKey = "user_1";

        System.out.println("Attempting 5 rapid requests (Capacity: 3)...");
        for (int i = 1; i <= 5; i++) {
            boolean allowed = tokenBucket.allowRequest(clientKey);
            System.out.printf("  Request %d: %s%n", i, allowed ? "ALLOWED (200 OK)" : "REJECTED (429 Too Many Requests)");
        }

        System.out.println("\nSleeping 1050ms for bucket to refill...");
        Thread.sleep(1050);

        System.out.println("Attempting 2 requests post-refill...");
        for (int i = 6; i <= 7; i++) {
            boolean allowed = tokenBucket.allowRequest(clientKey);
            System.out.printf("  Request %d: %s%n", i, allowed ? "ALLOWED (200 OK)" : "REJECTED (429 Too Many Requests)");
        }
        System.out.println();
    }

    private static void testLeakyBucket() throws InterruptedException {
        System.out.println("--- [2] Testing Leaky Bucket Rate Limiter (Via Factory) ---");
        // Capacity: 3 requests, Time window: 1000ms
        RateLimiter leakyBucket = RateLimiterFactory.createRateLimiter(
                RateLimiterFactory.Algorithm.LEAKY_BUCKET, 3, 1000, 0
        );
        String clientKey = "user_2";

        System.out.println("Filling leaky bucket with 5 rapid requests (Capacity: 3)...");
        for (int i = 1; i <= 5; i++) {
            boolean allowed = leakyBucket.allowRequest(clientKey);
            System.out.printf("  Request %d: %s%n", i, allowed ? "ACCEPTED INTO BUCKET" : "OVERFLOW REJECTED");
        }

        System.out.println("\nWaiting 700ms for bucket to leak partially...");
        Thread.sleep(700);

        System.out.println("Attempting new request after partial leak...");
        System.out.printf("  Request 6: %s%n", leakyBucket.allowRequest(clientKey) ? "ACCEPTED INTO BUCKET" : "OVERFLOW REJECTED");
        System.out.println();
    }

    private static void testSlidingWindowLog() throws InterruptedException {
        System.out.println("--- [3] Testing Sliding Window Log Rate Limiter (Via Factory) ---");
        // Limit: 3 requests per 1000ms window
        RateLimiter slidingWindow = RateLimiterFactory.createRateLimiter(
                RateLimiterFactory.Algorithm.SLIDING_WINDOW_LOG, 3, 1000, 0
        );
        String clientKey = "user_3";

        System.out.println("Executing 4 immediate requests (Limit: 3 per sec)...");
        for (int i = 1; i <= 4; i++) {
            boolean allowed = slidingWindow.allowRequest(clientKey);
            System.out.printf("  Request %d: %s%n", i, allowed ? "ALLOWED" : "RATE LIMITED");
        }

        System.out.println("\nWaiting 1100ms for old timestamps to expire...");
        Thread.sleep(1100);

        System.out.println("Attempting request after window reset...");
        System.out.printf("  Request 5: %s%n", slidingWindow.allowRequest(clientKey) ? "ALLOWED (Old logs pruned)" : "RATE LIMITED");
        System.out.println();
    }
}