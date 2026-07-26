package amazoninternal.lld.ratelimiter;

public class RateLimiterFactory {
    public enum Algorithm {
        TOKEN_BUCKET,
        LEAKY_BUCKET,
        SLIDING_WINDOW_LOG
    }

    public static RateLimiter createRateLimiter(Algorithm algorithm, int maxRequests, long timeWindowMs, int refillTokens) {
        switch (algorithm) {
            case TOKEN_BUCKET:
                return new TokenBucketRateLimiter(maxRequests, refillTokens, timeWindowMs);
            case LEAKY_BUCKET:
                return new LeakyBucketRateLimiter(maxRequests);
            case SLIDING_WINDOW_LOG:
                return new SlidingWindowLogRateLimiter(maxRequests, timeWindowMs);
            default:
                throw new IllegalArgumentException("Unsupported algorithm: " + algorithm);
        }
    }
}
