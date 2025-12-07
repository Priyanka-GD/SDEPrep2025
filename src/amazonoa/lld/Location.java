package amazonoa.lld;

import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class Location {
    String locationId;
    int openingTime;
    int closingTime;
    List<Locker> lockerList;
    int latitude;
    int longitude;
    private final ReentrantLock locationLock = new ReentrantLock();

    public void lock() {
        locationLock.lock();
    }

    public void unlock() {
        locationLock.unlock();
    }

    public Location(String locationId, int openingTime, int closingTime, int latitude, int longitude) {
        this.locationId = locationId;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
