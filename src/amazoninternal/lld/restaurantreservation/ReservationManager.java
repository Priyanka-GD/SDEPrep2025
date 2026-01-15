package amazoninternal.lld.restaurantreservation;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ReservationManager {
    private List<Table> tables;
    private TableAllocationStrategy allocationStrategy;
    // We will use a simple list for the waitlist for now
    private List<Customer> waitlist;

    public ReservationManager(TableAllocationStrategy strategy) {
        this.tables = new ArrayList<>();
        this.waitlist = new ArrayList<>();
        this.allocationStrategy = strategy;
    }

    public void addTable(Table table) {
        tables.add(table);
    }

    public void setStrategy(TableAllocationStrategy strategy) {
        this.allocationStrategy = strategy;
    }

    /**
     * The core method to seat a customer
     */
    public Table findAndOccupyTable(int partySize, Customer customer) {
        // 1. Get only available tables
        List<Table> availableTables = tables.stream()
                .filter(t -> t.currentState instanceof AvailableState)
                .collect(Collectors.toList());

        // 2. Use the strategy to pick the best one
        Table selectedTable = allocationStrategy.allocate(availableTables, partySize);

        if (selectedTable != null) {
            selectedTable.occupy(); // This triggers the State Pattern transition
            System.out.println("Assigned Table #" + selectedTable.getTableId() + " to " + customer.getName());
            return selectedTable;
        } else {
            System.out.println("No tables available. Adding " + customer.getName() + " to waitlist.");
            waitlist.add(customer);
            return null;
        }
    }
}