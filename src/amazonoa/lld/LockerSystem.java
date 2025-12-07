package amazonoa.lld;

import java.time.LocalTime;
import java.util.List;


public class LockerSystem {
    List<Location> locationList;

    public LockerSystem(List<Location> locationList) {
        this.locationList = locationList;
    }

    public Locker assignLocker(Customer customer, Package pack) {
        String code = customer.userId + pack.packageId;
        //Find locker
        int lockerCode = code.hashCode();
        Location location = getClosestLocation(locationList, customer.longitude, customer.latitude);
        if (location == null || !isLocationOpen(location)) {
            throw new RuntimeException("No open location found near customer.");
        }

        Locker locker = placePackageInLocker(location, pack);
        if (locker == null) {
            throw new RuntimeException("No available locker for package size " + pack.size);
        }
        locker.pin = lockerCode;
        return locker;
    }

    private boolean isLocationOpen(Location location) {
        int currentTime = getCurrentTime(); // You can mock this for now
        return currentTime >= location.openingTime && currentTime <= location.closingTime;
    }

    private Locker placePackageInLocker(Location location, Package pack) {
        List<Locker> lockers = location.lockerList;
        for (Locker locker : lockers) {
            locker.lock();
            try {
                if (locker.isAvailable && pack.size == locker.size) {
                    locker.isAvailable = false;
                    return locker;
                }
            } finally {
                locker.unlock();
            }
        }
        return null;
    }

    private Location getClosestLocation(List<Location> locationList, int longitude, int latitude) {
        int minValue = Integer.MAX_VALUE;
        Location targetLocation = null;
        for (Location location : locationList) {
            int distance = getEuclideanDistance(longitude, latitude, location.longitude, location.latitude);
            if (minValue > distance) {
                minValue = distance;
                targetLocation = location;
            }
        }
        return targetLocation;
    }

    private int getEuclideanDistance(int userLongitude, int userLatitude, int longitude, int latitude) {
        int diffLongitude = longitude - userLongitude;
        int diffLatitude = latitude - userLatitude;
        return (diffLatitude * diffLatitude) + (diffLongitude * diffLongitude);
    }

    public boolean unassignLocker(String lockerId, int pin) {
        for (Location location : locationList) {
            for (Locker locker : location.lockerList) {
                locker.lock();
                try {
                    if (locker.lockerId.equals(lockerId) && locker.pin == pin) {
                        locker.isAvailable = true;
                        locker.pin = 0;
                        return true;
                    }
                } finally {
                    locker.unlock();
                }
            }
        }
        return false;
    }

    public boolean customerPickup(String lockerId, int pin) {
        boolean result = unassignLocker(lockerId, pin);
        if (result) {
            System.out.println("Package retrieved successfully. Locker is now available.");
        } else {
            System.out.println("Invalid PIN or Locker ID.");
        }
        return result;
    }


    private int getCurrentTime() {
        LocalTime now = LocalTime.now();
        int hours = now.getHour();
        int minutes = now.getMinute();
        return hours * 100 + minutes;
    }
}
