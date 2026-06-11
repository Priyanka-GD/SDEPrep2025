package amazoninternal.lld.lockermanagement;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Initializing Locker Management System ===");
        InventoryManager inventoryManager = new InventoryManager();

        // 1. Seed some initial lockers into the system
        System.out.println("\n--- Creating Initial Warehouse Lockers ---");
        Locker smallLocker1 = inventoryManager.createNewLocker(Size.Small);
        Locker mediumLocker1 = inventoryManager.createNewLocker(Size.Medium);

        System.out.println("Created Small Locker ID: " + smallLocker1.getLockerId());
        System.out.println("Created Medium Locker ID: " + mediumLocker1.getLockerId());

        // 2. Setup incoming warehouse packages
        System.out.println("\n--- Registering Incoming Packages ---");

        Package pkg1 = new Package();
        pkg1.setPackageId("PKG-AMZN-001");
        pkg1.setSize(Size.Small);

        Package pkg2 = new Package();
        pkg2.setPackageId("PKG-AMZN-002");
        pkg2.setSize(Size.Small); // This will force a new Small locker to be made!

        Package pkg3 = new Package();
        pkg3.setPackageId("PKG-AMZN-003");
        pkg3.setSize(Size.Medium);

        inventoryManager.registerPackage(pkg1);
        inventoryManager.registerPackage(pkg2);
        inventoryManager.registerPackage(pkg3);

        System.out.println("Registered 3 packages into incoming inventory.");

        // 3. Assign packages to available lockers
        System.out.println("\n--- Assigning Lockers ---");

        // Should occupy smallLocker1
        inventoryManager.assignLocker(pkg1.getPackageId());

        // No small lockers left! Should dynamically spin up smallLocker2
        inventoryManager.assignLocker(pkg2.getPackageId());

        // Should occupy mediumLocker1
        inventoryManager.assignLocker(pkg3.getPackageId());

        // 4. Remove a package and free the locker back up
        System.out.println("\n--- Processing Package Collection ---");
        inventoryManager.removePackage(pkg1.getPackageId());

        // 5. Reassign to see if the freed locker gets reused
        System.out.println("\n--- Testing Locker Reuse ---");
        Package pkg4 = new Package();
        pkg4.setPackageId("PKG-AMZN-004");
        pkg4.setSize(Size.Small);

        inventoryManager.registerPackage(pkg4);
        // This should go smoothly into the Small locker we just freed, instead of making a 3rd one.
        inventoryManager.assignLocker(pkg4.getPackageId());

        System.out.println("\n=== Workflow Complete ===");
    }
}