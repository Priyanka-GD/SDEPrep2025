package amazoninternal.lld.restaurantreservation;

public class ReservedState implements TableState {
    @Override
    public void occupy(Table table) {
        System.out.println("Reserved guest has arrived. Seating now...");
        table.setState(new OccupiedState());
    }
    @Override
    public void reserve(Table table) {
        throw new IllegalStateException("Table is already reserved!");
    }
    // These remain Illegal because you can't vacate or clean a table that is already empty
    @Override
    public void vacate(Table table) {
        throw new IllegalStateException("Table is already empty!");
    }

    @Override
    public void clean(Table table) {
        throw new IllegalStateException("Table is already clean!");
    }
}