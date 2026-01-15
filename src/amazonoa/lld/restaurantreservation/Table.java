package amazonoa.lld.restaurantreservation;

public class Table {
    int capacity;
    int tableId;
    TableState currentState;

    public void setState(TableState tableState){
        this.currentState = tableState;
    }

    public Table(int tableId, int capacity){
        this.tableId = tableId;
        this.capacity = capacity;
        this.currentState = new AvailableState();
    }

    public int getCapacity() {
        return capacity;
    }

    public TableState getCurrentState() {
        return currentState;
    }

    public void occupy() {
        currentState.occupy(this);
    }

    public int getTableId() {
        return tableId;
    }

    public void vacate() {
        currentState.vacate(this);
    }

    public void clean() {
        currentState.clean(this);
    }
}
