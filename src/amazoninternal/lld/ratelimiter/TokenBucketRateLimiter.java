package amazoninternal.lld.ratelimiter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TokenBucketRateLimiter implements RateLimiter {
    private final double capacity;
    private final double refillRate; // tokens per millisecond
    private final Map<String, Bucket> buckets = new ConcurrentHashMap<>();

    public TokenBucketRateLimiter(int capacity, int refillTokens, long timeWindowMs) {
        this.capacity = capacity;
        this.refillRate = (double) refillTokens / timeWindowMs;
    }

    @Override
    public boolean allowRequest(String key) {
        long now = System.currentTimeMillis();
        Bucket bucket = buckets.computeIfAbsent(key, k -> new Bucket(capacity, now));

        synchronized (bucket) {
            // Lazy refill evaluation based on elapsed time
            long elapsedTime = now - bucket.lastRefillTimestamp;
            double tokensToAdd = elapsedTime * refillRate;

            if (tokensToAdd > 0) {
                bucket.tokens = Math.min(capacity, bucket.tokens + tokensToAdd);
                bucket.lastRefillTimestamp = now;
            }

            if (bucket.tokens >= 1.0) {
                bucket.tokens -= 1.0;
                return true;
            }
            return false;
        }
    }

    private static class Bucket {
        double tokens;
        long lastRefillTimestamp;

        public Bucket(double capacity, long timestamp) {
            this.tokens = capacity;
            this.lastRefillTimestamp = timestamp;
        }
    }
}