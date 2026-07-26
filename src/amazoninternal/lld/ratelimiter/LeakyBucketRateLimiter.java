package amazoninternal.lld.ratelimiter;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class LeakyBucketRateLimiter implements RateLimiter {
    Map<String, Queue<Long>> buckets;
    private int capacity;

    public LeakyBucketRateLimiter(int capacity) {
        buckets = new HashMap<>();
        this.capacity = capacity;
    }

    @Override
    public boolean allowRequest(String key) {
        Queue<Long> bucket = buckets.computeIfAbsent(key, k -> new ConcurrentLinkedQueue<>());
        synchronized (bucket) {
            if (bucket.size() < capacity) {
                bucket.add(System.currentTimeMillis());
                return true;
            }
        }
        return false;
    }
}
