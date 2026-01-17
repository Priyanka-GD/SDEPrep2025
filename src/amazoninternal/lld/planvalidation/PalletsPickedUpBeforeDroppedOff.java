package amazoninternal.lld.planvalidation;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PalletsPickedUpBeforeDroppedOff implements Validation {

    @Override
    public void validPlan(List<Event> events) {
        if (events == null)
            throw new IllegalArgumentException("Events is empty");

        Set<Integer> palletsInTransit = new HashSet<>();

        for (Event event : events) {
            int palletId = event.getPallet();

            if (event.operation == Operation.PICKUP) {
                palletsInTransit.add(palletId);
            } else if (event.operation == Operation.DROP) {
                if (!palletsInTransit.contains(palletId)) {
                    throw new IllegalArgumentException("Pallet: " + palletId + " dropped off without being picked up.");
                }

                palletsInTransit.remove(palletId);
            }
        }

        System.out.println("PalletsPickedUpBeforeDroppedOff is validated");

    }
}