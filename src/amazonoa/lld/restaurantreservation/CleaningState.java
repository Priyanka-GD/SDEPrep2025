package amazonoa.lld.restaurantreservation;

public class CleaningState implements TableState {
    @Override
    public void occupy(Table table) {
        throw new IllegalStateException("Cannot seat guests while table is being cleaned.");
    }

    @Override
    public void reserve(Table table) {
        throw new IllegalStateException("Cannot reserve while cleaning.");
    }

    @Override
    public void vacate(Table table) {
        throw new IllegalStateException("Table is already vacant.");
    }

    @Override
    public void clean(Table table) {
        System.out.println("Cleaning finished for Table #" + table.getTableId());
        table.setState(new AvailableState());
    }
}