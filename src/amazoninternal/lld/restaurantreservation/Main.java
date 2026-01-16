package amazoninternal.lld.restaurantreservation;

public class Main {
    public static void main(String[] args) {
        // 1. Setup system with BestFit Strategy
        TableAllocationStrategy strategy = new BestFitStrategy();
        ReservationManager manager = new ReservationManager(strategy);

        // 2. Setup Restaurant Tables
        manager.addTable(new Table(1, 2)); // Table #1
        manager.addTable(new Table(2, 4)); // Table #2
        manager.addTable(new Table(3, 6)); // Table #3

        System.out.println("--- Scenario 1: Alice wants to eat NOW (Occupy) ---");
        Customer alice = new Customer("C1", "Alice", "123-456", 2);
        Table alicesTable = manager.findAndOccupyTable(2, alice);
        // Table #1 is now in OccupiedState

        System.out.println("\n--- Scenario 2: Bob wants to book for later (Reserve) ---");
        Customer bob = new Customer("C2", "Bob", "987-654", 2);
        Table bobsTable = manager.findAndReserveTable(2, bob);
        // Table #1 is busy, Table #2 is 4-seater, Table #3 is 6-seater.
        // BestFit picks Table #2. It is now in ReservedState.

        System.out.println("\n--- Scenario 3: Alice finishes and table is cleaned ---");
        if (alicesTable != null) {
            alicesTable.vacate(); // Transitions: Occupied -> Cleaning
            alicesTable.clean();  // Transitions: Cleaning -> Available
            System.out.println("Table #" + alicesTable.getTableId() + " is now " + alicesTable.getCurrentState().getClass().getSimpleName());
        }

        System.out.println("\n--- Scenario 4: Bob arrives for his reservation ---");
        if (bobsTable != null) {
            // Because Bob's table is in ReservedState, occupy() will work
            bobsTable.occupy(); // Transitions: Reserved -> Occupied
            System.out.println("Bob's Table status: " + bobsTable.getCurrentState().getClass().getSimpleName());
        }

        System.out.println("\n--- Scenario 5: Charlie tries to take Table #2 (Bob's table) ---");
        Customer charlie = new Customer("C3", "Charlie", "111-222", 4);
        Table charliesTable = manager.findAndOccupyTable(4, charlie);
        // Table #2 is Occupied by Bob, Table #1 is Available (cleaned), Table #3 is Available.
        // Charlie needs 4 seats. Table #1 is too small. BestFit picks Table #3.
    }
}