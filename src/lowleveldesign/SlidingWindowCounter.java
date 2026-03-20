package lowleveldesign;

import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class SlidingWindowCounter {
    private final int limit;
    // Map stores: "userId:minuteTimestamp" -> Counter
    private final Map<String, AtomicInteger> counters = new ConcurrentHashMap<>();

    public SlidingWindowCounter(int limit) {
        this.limit = limit;
    }

    public boolean isAllowed(String userId) {
        long currentTime = System.currentTimeMillis();
        long currentMinute = currentTime / 60000;
        long previousMinute = currentMinute - 1;

        // 1. Get counts for current and previous windows
        int currentCount = getCount(userId, currentMinute);
        int previousCount = getCount(userId, previousMinute);

        // 2. Calculate the weight of the previous window
        // (60000 - ms passed in current minute) / 60000
        double elapsedInCurrentMinute = currentTime % 60000;
        double weight = (60000 - elapsedInCurrentMinute) / 60000.0;

        // 3. Estimate total requests in the sliding window
        double estimate = (previousCount * weight) + currentCount;

        if (estimate < limit) {
            // Increment current minute counter
            counters.get(userId + ":" + currentMinute).incrementAndGet();
            return true;
        }

        return false;
    }

    private int getCount(String userId, long minute) {
        String key = userId + ":" + minute;
        counters.putIfAbsent(key, new AtomicInteger(0));
        return counters.get(key).get();
    }
}