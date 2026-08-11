package amazoninternal.lld.ratelimiter;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SlidingWindowLogRateLimiter implements RateLimiter {
    private final int maxRequests;
    private final long windowSizeInMillis;
    private final Map<String, Deque<Long>> userLogs = new ConcurrentHashMap<>();

    public SlidingWindowLogRateLimiter(int maxRequests, long windowSizeInMillis) {
        this.maxRequests = maxRequests;
        this.windowSizeInMillis = windowSizeInMillis;
    }

    @Override
    public boolean allowRequest(String key) {
        long now = System.currentTimeMillis();
        long windowBoundary = now - windowSizeInMillis;

        Deque<Long> timestamps = userLogs.computeIfAbsent(key, k -> new ArrayDeque<>());

        synchronized (timestamps) {
            // Remove outdated timestamps outside current window
            while (!timestamps.isEmpty() && timestamps.peekFirst() <= windowBoundary) {
                timestamps.pollFirst();
            }

            if (timestamps.size() < maxRequests) {
                timestamps.addLast(now);
                return true;
            }
            return false;
        }
    }
}