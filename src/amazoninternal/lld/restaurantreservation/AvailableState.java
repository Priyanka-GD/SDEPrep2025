package amazoninternal.lld.restaurantreservation;

public class AvailableState implements TableState {
    @Override
    public void occupy(Table table) {
        System.out.println("Seating guests... Table is now occupied.");
        table.setState(new OccupiedState());
    }

    // Inside AvailableState.java
    @Override
    public void reserve(Table table) {
        System.out.println("Table #" + table.getTableId() + " is now Reserved.");
        table.setState(new ReservedState());
    }

    // These remain Illegal because you can't vacate or clean a table that is already empty
    @Override public void vacate(Table table) { throw new IllegalStateException("Table is already empty!"); }
    @Override public void clean(Table table) { throw new IllegalStateException("Table is already clean!"); }
}