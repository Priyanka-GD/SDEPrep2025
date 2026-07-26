package amazoninternal.lld.ratelimiter;

public interface RateLimiter {
    boolean allowRequest(String key);
}
