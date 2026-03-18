package lowleveldesign;

import java.util.HashMap;
import java.util.Map;

public class TokenBucket {
    static Map<String, long[]> mapOfUserSessions = new HashMap<>();
    static int MAX_TOKEN_LIMIT = 5;
    static long REFILL_INTERVAL_MS = 60000;

    public static void main(String[] args) {
        String userId = "abc";
        long currentTime = System.currentTimeMillis();
        mapOfUserSessions.putIfAbsent(userId, new long[]{MAX_TOKEN_LIMIT, REFILL_INTERVAL_MS});

        // Simulate 6 rapid requests
        for (int i = 0; i < 10; i++) {
            boolean allowed = tryConsumingRequest(userId, currentTime);
            System.out.println("Request " + (i + 1) + ": " + (allowed ? "Accepted" : "Rejected"));
        }

        // Simulate 1 minute passing
        System.out.println("\n--- 1 Minute Passes ---");
        currentTime += 60001;

        boolean allowedAfterWait = tryConsumingRequest(userId, currentTime);
        System.out.println("Request after wait: " + (allowedAfterWait ? "Accepted" : "Rejected"));
    }

    public static boolean tryConsumingRequest(String userId, long currentReqTime) {
        long[] value = mapOfUserSessions.get(userId);
        long currentTokens = value[0];
        long lastRefillTime = value[1];

        // 1. Check if it's time to refill
        if (currentReqTime - lastRefillTime >= REFILL_INTERVAL_MS) {
            currentTokens = MAX_TOKEN_LIMIT;
            lastRefillTime = currentReqTime; // Start the clock for the next minute
        }

        // 2. Try to consume a token
        if (currentTokens > 0) {
            currentTokens--;
            // 3. Save the updated state back to the array
            value[0] = currentTokens;
            value[1] = lastRefillTime;
            return true;
        }

        // Even if we reject, we must save the refill time if it was updated
        value[1] = lastRefillTime;
        return false;
    }

}
