package amazonoa.lld.restaurantreservation;

public interface TableState {
    void reserve(Table table);
    void occupy(Table table);
    void vacate(Table table);
    void clean(Table table);
}
