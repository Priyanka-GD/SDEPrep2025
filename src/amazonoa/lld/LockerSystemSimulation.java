package amazonoa.lld;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class LockerSystemSimulation{

    public static void main(String[] args) throws InterruptedException {
        // Setup: Create a location with 5 small lockers
        List<Locker> lockers = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            lockers.add(new Locker(Size.SMALL, true, "L" + i, 0));
        }
        Location location = new Location("Loc1", 800, 2200, 37, -122);
        location.lockerList = lockers;

        // Initialize LockerSystem
        LockerSystem lockerSystem = new LockerSystem(List.of(location));

        // Create 10 customers (more than available lockers to force contention)
        int customerCount = 10;
        CountDownLatch latch = new CountDownLatch(1);  // <-- This is the key fix
        List<Thread> threads = new ArrayList<>();

        for (int i = 1; i <= customerCount; i++) {
            final int customerId = i;
            Thread t = new Thread(() -> {
                try {
                    latch.await();  // All threads wait here until latch is released
                    Customer customer = new Customer("Customer" + customerId, "Address" + customerId, "U" + customerId, 37, -122);
                    Package pack = new Package(Size.SMALL, "Package" + customerId, "P" + customerId);
                    try {
                        Locker locker = lockerSystem.assignLocker(customer, pack);
                        System.out.println("Customer " + customerId + " got Locker: " + locker.lockerId + " with PIN: " + locker.pin);
                    } catch (RuntimeException e) {
                        System.out.println("Customer " + customerId + " failed to get a locker: " + e.getMessage());
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
            threads.add(t);
            t.start();
        }

        Thread.sleep(100);  // Just to ensure all threads are ready
        latch.countDown();  // All threads are released at the same time

        // Wait for all threads to complete
        for (Thread t : threads) {
            t.join();
        }

        System.out.println("Test Completed");
    }
}
