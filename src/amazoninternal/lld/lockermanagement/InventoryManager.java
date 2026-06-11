package amazoninternal.lld.lockermanagement;

import java.util.*;

public class InventoryManager {
    private final Map<String, String> mapOfPackageToLocker;
    private final Map<String, Locker> mapOfLockerIdToLocker;
    private final Map<String, Package> mapOfPackageIdToPackage;
    private final Map<Size, List<String>> mapOfLockerIdsToSize;

    public InventoryManager() {
        mapOfLockerIdToLocker = new HashMap<>();
        mapOfPackageToLocker = new HashMap<>();
        mapOfLockerIdsToSize = new HashMap<>();
        mapOfPackageIdToPackage = new HashMap<>();

        for (Size size : Size.values()) {
            mapOfLockerIdsToSize.put(size, new ArrayList<>());
        }
    }

    // Synchronized to protect shared maps during manual additions
    public synchronized Locker createNewLocker(Size size) {
        Locker locker = new Locker(size, UUID.randomUUID().toString());
        mapOfLockerIdToLocker.put(locker.getLockerId(), locker);
        mapOfLockerIdsToSize.get(size).add(locker.getLockerId());
        return locker;
    }

    public synchronized void registerPackage(Package pkg) {
        mapOfPackageIdToPackage.put(pkg.getPackageId(), pkg);
    }

    // Synchronized to prevent two packages grabbing the same locker
    public synchronized void assignLocker(String pkgId) {
        Package pkg = mapOfPackageIdToPackage.getOrDefault(pkgId, null);
        if (pkg == null) {
            throw new NoSuchElementException("No Package with id " + pkgId + " is found ");
        }

        boolean isAssigned = false;
        String lckId = null;

        for (String lockerId : mapOfLockerIdsToSize.get(pkg.getSize())) {
            Locker locker = mapOfLockerIdToLocker.get(lockerId);
            if (locker != null && locker.isAvailable()) {
                mapOfPackageToLocker.put(pkg.getPackageId(), lockerId);
                locker.setAvailable(false);
                lckId = lockerId;
                isAssigned = true;
                break;
            }
        }

        if (!isAssigned) {
            Locker locker = createNewLocker(pkg.getSize());
            mapOfPackageToLocker.put(pkg.getPackageId(), locker.getLockerId());
            locker.setAvailable(false);
            lckId = locker.getLockerId();
        }

        System.out.println("Package with id " + pkg.getPackageId() + " is assigned to locker with id " + lckId);
    }

    // Synchronized to safely update maps and status flags
    public synchronized void removePackage(String pckId) {
        if (mapOfPackageToLocker.containsKey(pckId)) {
            String lockerId = mapOfPackageToLocker.remove(pckId);

            Locker locker = mapOfLockerIdToLocker.get(lockerId);
            if (locker != null) {
                locker.setAvailable(true);
            }

            mapOfPackageIdToPackage.remove(pckId);
            System.out.println("Package " + pckId + " removed. Locker " + lockerId + " is now free.");
        }
    }
}