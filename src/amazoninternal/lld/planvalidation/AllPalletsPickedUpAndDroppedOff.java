package amazoninternal.lld.planvalidation;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class AllPalletsPickedUpAndDroppedOff implements Validation {

    @Override
    public void validPlan(List<Event> events) {
        if (events == null)
            throw new IllegalArgumentException("Events is empty");

        Set<Integer> pickedUpIds = getPalletIdsPerOperation(events, Operation.PICKUP);

        Set<Integer> droppedOffIds = getPalletIdsPerOperation(events, Operation.DROP);

        if (!droppedOffIds.containsAll(pickedUpIds)) {
            pickedUpIds.removeAll(droppedOffIds);
            throw new IllegalArgumentException("Pallets picked up but never dropped: " + pickedUpIds);
        }

        if (!pickedUpIds.containsAll(droppedOffIds)) {
            droppedOffIds.removeAll(pickedUpIds);
            throw new IllegalArgumentException("Pallets dropped off but never picked up: " + droppedOffIds);
        }
        System.out.println("AllPalletsPickedUpAndDroppedOff is validated");
    }

    private Set<Integer> getPalletIdsPerOperation(List<Event> events, Operation operation) {
        return events.stream()
                .filter(e -> e.operation == operation)
                .map(Event::getPallet)
                .collect(Collectors.toSet());
    }
}