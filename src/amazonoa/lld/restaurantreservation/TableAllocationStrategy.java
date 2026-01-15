package amazonoa.lld.restaurantreservation;

import java.util.List;

public interface TableAllocationStrategy {
    Table allocate(List<Table> availableTables, int partySize);
}