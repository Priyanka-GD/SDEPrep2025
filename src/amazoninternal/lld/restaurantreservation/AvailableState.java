package amazoninternal.lld.restaurantreservation;

public class AvailableState implements TableState {
    @Override
    public void occupy(Table table) {
        System.out.println("Seating guests... Table is now occupied.");
        // Change the state of the table to Occupied
        table.setState(new OccupiedState());
    }

    @Override
    public void reserve(Table table) {
        System.out.println("Reserving table...");
        // You would typically move to a ReservedState here
    }

    // These remain Illegal because you can't vacate or clean a table that is already empty
    @Override public void vacate(Table table) { throw new IllegalStateException("Table is already empty!"); }
    @Override public void clean(Table table) { throw new IllegalStateException("Table is already clean!"); }
}