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

    private Table searchTable(int partySize) {
        return allocationStrategy.allocate(tables, partySize);
    }
    /**
     * The core method to seat a customer
     */
    public Table findAndReserveTable(int partySize, Customer customer) {
        Table selectedTable = searchTable(partySize);
        if (selectedTable != null) {
            selectedTable.reserve(); // Correct: Transitions to ReservedState
            System.out.println("Reserved Table #" + selectedTable.getTableId() + " for " + customer.getName());
            return selectedTable;
        }
        return handleWaitlist(customer);
    }

    public Table findAndOccupyTable(int partySize, Customer customer) {
        Table selectedTable = searchTable(partySize);
        if (selectedTable != null) {
            selectedTable.occupy(); // Correct: Transitions to OccupiedState
            System.out.println("Occupied Table #" + selectedTable.getTableId() + " for " + customer.getName());
            return selectedTable;
        }
        return handleWaitlist(customer);
    }

    private Table handleWaitlist(Customer customer) {
        System.out.println("No tables available. Adding " + customer.getName() + " to waitlist.");
        waitlist.add(customer);
        return null;
    }
}