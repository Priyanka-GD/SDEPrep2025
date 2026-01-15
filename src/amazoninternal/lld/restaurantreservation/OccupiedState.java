package amazoninternal.lld.restaurantreservation;

public class OccupiedState implements TableState {
    @Override
    public void occupy(Table table) {
        throw new IllegalStateException("Table is already occupied.");
    }

    @Override
    public void reserve(Table table) {
        throw new IllegalStateException("Cannot reserve an occupied table.");
    }

    @Override
    public void vacate(Table table) {
        System.out.println("Guests have left Table #" + table.getTableId() + ". It is now dirty.");
        table.setState(new CleaningState());
    }

    @Override
    public void clean(Table table) {
        throw new IllegalStateException("Cannot clean while guests are eating.");
    }
}