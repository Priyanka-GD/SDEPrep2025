package amazoninternal.lld.restaurantreservation;

import java.util.Comparator;
import java.util.List;

public class BestFitStrategy implements TableAllocationStrategy {
    @Override
    public Table allocate(List<Table> availableTables, int partySize) {
        return availableTables.stream()
                .filter(t -> t.getCapacity() >= partySize)
                .filter(t -> t.getCurrentState() instanceof AvailableState)
                .min(Comparator.comparingInt(Table::getCapacity))
                .orElse(null); // No suitable table found
    }
}