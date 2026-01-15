package amazoninternal.lld.restaurantreservation;
public class Main {
    public static void main(String[] args) {
        // 1. Setup the system with a specific Strategy
        // We start with BestFit to ensure we don't waste large tables
        TableAllocationStrategy strategy = new BestFitStrategy();
        ReservationManager manager = new ReservationManager(strategy);

        // 2. Add tables to the restaurant
        Table t1 = new Table(1, 2); // Small table
        Table t2 = new Table(2, 4); // Medium table
        Table t3 = new Table(3, 6); // Large family table

        manager.addTable(t1);
        manager.addTable(t2);
        manager.addTable(t3);

        System.out.println("--- Scenario 1: Seating a party of 2 ---");
        Customer alice = new Customer("C1", "Alice", "123-456", 2);
        Table alicesTable = manager.findAndOccupyTable(2, alice);
        // Expectation: BestFit should pick Table #1 (capacity 2)

        System.out.println("\n--- Scenario 2: Seating a party of 3 ---");
        Customer bob = new Customer("C2", "Bob", "987-654", 3);
        Table bobsTable = manager.findAndOccupyTable(3, bob);
        // Expectation: Table #1 is occupied, so it should pick Table #2 (capacity 4)

        System.out.println("\n--- Scenario 3: Table Lifecycle ---");
        if (alicesTable != null) {
            System.out.println("Alice is finishing her meal...");
            alicesTable.vacate(); // Moves to VacatingState

            System.out.println("Staff is cleaning Alice's table...");
            alicesTable.clean();  // Moves to CleaningState

            // Note: To move back to AvailableState, you would call a 'finishCleaning'
            // method that sets the state back to AvailableState.
        }

        System.out.println("\n--- Scenario 4: Switching Strategy at Runtime ---");
        // Imagine it's late night, we want to consolidate guests
        manager.setStrategy(new BestFitStrategy());
        System.out.println("Strategy switched to Section Prioritization.");
    }
}