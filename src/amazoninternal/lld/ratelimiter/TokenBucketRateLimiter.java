package amazoninternal.lld.ratelimiter;

import java.util.HashMap;
import java.util.Map;

public class TokenBucketRateLimiter implements RateLimiter {
    int capacity;
    Map<String, Bucket> buckets;
    double refillRate;

    public TokenBucketRateLimiter(int capacity, int refillToken, long requestTime) {
        this.capacity = capacity;
        buckets = new HashMap<>();
        refillRate = (double) refillToken / requestTime;
    }

    @Override
    public boolean allowRequest(String key) {
        long now = System.currentTimeMillis();
        Bucket bucket = buckets.computeIfAbsent(key, k -> new Bucket(capacity, now));

        synchronized (bucket) {
            long elapsedTime = now - bucket.lastRefillTimestamp;
            double tokensToAdd = elapsedTime * refillRate;
            if (tokensToAdd > 0) {
                bucket.tokens = Math.min(capacity, (long) tokensToAdd + bucket.tokens);
                bucket.lastRefillTimestamp = now;
            }

            if (bucket.tokens > 0) {
                bucket.tokens--;
                return true;
            }
        }

        return false;
    }

    class Bucket {
        long tokens;
        long lastRefillTimestamp;

        public Bucket(long capacity, long timestamp) {
            this.tokens = capacity;
            this.lastRefillTimestamp = timestamp;
        }
    }
}

