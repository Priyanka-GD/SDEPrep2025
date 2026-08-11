package amazoninternal.lld.ratelimiter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LeakyBucketRateLimiter implements RateLimiter {
    private final int capacity;
    private final double leakRate; // items leaked per millisecond
    private final Map<String, Bucket> buckets = new ConcurrentHashMap<>();

    public LeakyBucketRateLimiter(int capacity, double leakRatePerMs) {
        this.capacity = capacity;
        this.leakRate = leakRatePerMs;
    }

    @Override
    public boolean allowRequest(String key) {
        long now = System.currentTimeMillis();
        Bucket bucket = buckets.computeIfAbsent(key, k -> new Bucket(now));

        synchronized (bucket) {
            // Leak items out of the bucket based on time elapsed
            long elapsedTime = now - bucket.lastLeakTimestamp;
            double leakedCount = elapsedTime * leakRate;

            if (leakedCount > 0) {
                bucket.waterLevel = Math.max(0, bucket.waterLevel - leakedCount);
                bucket.lastLeakTimestamp = now;
            }

            // Check if bucket has room for 1 more unit of water (request)
            if (bucket.waterLevel + 1.0 <= capacity) {
                bucket.waterLevel += 1.0;
                return true;
            }
            return false;
        }
    }

    private static class Bucket {
        double waterLevel;
        long lastLeakTimestamp;

        public Bucket(long timestamp) {
            this.waterLevel = 0.0;
            this.lastLeakTimestamp = timestamp;
        }
    }
}