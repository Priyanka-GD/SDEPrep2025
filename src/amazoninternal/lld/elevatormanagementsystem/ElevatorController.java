package amazoninternal.lld.elevatormanagementsystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ElevatorController {
    static List<Elevator> elevators;
    int totalFloors;

    public ElevatorController(int instances, int floors) {
        elevators = new ArrayList<>();
        for (int i = 0; i < instances; i++) {
            Elevator elevator = new Elevator();
            elevators.add(elevator);
        }
        totalFloors = floors;
    }

    public boolean requestElevator(int floor, Direction direction) {
        if (floor > totalFloors || floor < 0) return false;
        if (direction == Direction.IDLE) return false; // Simple fix

        RequestType type = (direction == Direction.UP) ? RequestType.PICKUP_UP : RequestType.PICKUP_DOWN;

        // Try to find idle first, otherwise find best
        Elevator best = findIdleElevator().orElseGet(() -> findBestElevator(floor, direction).get());

        return best.addRequest(floor, type);
    }

    private Optional<Elevator> findBestElevator(int floor, Direction direction) {
        Optional<Elevator> best = Optional.empty();
        int dist = Integer.MAX_VALUE;
        for (Elevator e : elevators) {
            int absDist = Math.abs(e.currentFloor - floor);
            if (dist > absDist) {
                dist = absDist;
                best = Optional.of(e);
                best.get().direction = direction;
            }
        }
        return best;
    }

    private Optional<Elevator> findIdleElevator() {
        return elevators.stream().filter(e -> e.direction == Direction.IDLE).findFirst();
    }

    public void step() {
        for (Elevator elevator : elevators) {
            elevator.step();
        }
    }

    /* // Optimization in requestElevator
public boolean requestElevator(int floor, Direction direction) {
    if (floor > totalFloors || floor < 0) return false;

    Elevator best = null;
    int lowestCost = Integer.MAX_VALUE;

    for (Elevator e : elevators) {
        int cost = calculateCost(e, floor, direction);
        if (cost < lowestCost) {
            lowestCost = cost;
            best = e;
        }
    }

    RequestType type = (direction == Direction.UP) ? RequestType.PICKUP_UP : RequestType.PICKUP_DOWN;
    return best != null && best.addRequest(floor, type);
}*/
    private int calculateCost(Elevator e, int targetFloor, Direction requestDir) {
        int distance = Math.abs(e.currentFloor - targetFloor);

        // 1. IDEAL: Elevator is already moving in the direction we want and hasn't passed us yet.
        boolean movingToward = (e.direction == Direction.UP && e.currentFloor <= targetFloor) ||
                (e.direction == Direction.DOWN && e.currentFloor >= targetFloor);

        if (e.direction == requestDir && movingToward) {
            return distance; // No penalty
        }

        // 2. GOOD: Elevator is IDLE.
        if (e.direction == Direction.IDLE) {
            return distance + 2; // Small penalty because it has to start up
        }

        // 3. BAD: Elevator is moving away or is heading the opposite direction.
        // We give it a large penalty so it's only chosen as a last resort.
        return distance + totalFloors;
    }
}
