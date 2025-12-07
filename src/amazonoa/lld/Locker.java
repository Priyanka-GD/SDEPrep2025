package amazonoa.lld;

import java.util.concurrent.locks.ReentrantLock;

public class Locker {
    Size size;
    boolean isAvailable;
    String lockerId;
    int pin;
    private final ReentrantLock lock = new ReentrantLock();  // Add locker-level lock

    public Locker(Size size, boolean isAvailable, String lockerId, int pin) {
        this.size = size;
        this.isAvailable = isAvailable;
        this.lockerId = lockerId;
        this.pin = pin;
    }

    public void lock() {
        lock.lock();
    }

    public void unlock() {
        lock.unlock();
    }
}
