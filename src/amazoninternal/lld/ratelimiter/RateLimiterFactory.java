package amazoninternal.lld.ratelimiter;

public class RateLimiterFactory {

    public enum Algorithm {
        TOKEN_BUCKET,
        LEAKY_BUCKET,
        SLIDING_WINDOW_LOG
    }

    /**
     * Factory method to encapsulate instantiation logic.
     * * @param algorithm Algorithm choice
     * @param maxCapacity Max capacity / request limit per window
     * @param timeWindowMs Time window duration in milliseconds
     * @param refillTokens Number of tokens refilled per timeWindowMs (Token Bucket specific)
     */
    public static RateLimiter createRateLimiter(Algorithm algorithm, int maxCapacity, long timeWindowMs, int refillTokens) {
        switch (algorithm) {
            case TOKEN_BUCKET:
                return new TokenBucketRateLimiter(maxCapacity, refillTokens, timeWindowMs);
            case LEAKY_BUCKET:
                // leak rate = maxCapacity requests processed every timeWindowMs
                return new LeakyBucketRateLimiter(maxCapacity, (double) maxCapacity / timeWindowMs);
            case SLIDING_WINDOW_LOG:
                return new SlidingWindowLogRateLimiter(maxCapacity, timeWindowMs);
            default:
                throw new IllegalArgumentException("Unsupported algorithm: " + algorithm);
        }
    }
}