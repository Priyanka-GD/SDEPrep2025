package amazoninternal.lld.elevatormanagement;

import java.util.ArrayList;
import java.util.List;

public class ElevatorManager {
    private List<Elevator> elevators;
    private static ElevatorManager instance;

    public List<Elevator> getElevators() {
        return elevators;
    }

    private ElevatorManager(int numberOfElevators, int capacity) {
        elevators = new ArrayList<>();
        for (int i = 0; i < numberOfElevators; i++) {
            elevators.add(new Elevator(capacity));
        }
    }

    // Singleton Pattern for the Manager
    public static synchronized ElevatorManager getInstance(int count, int cap) {
        if (instance == null) {
            instance = new ElevatorManager(count, cap);
        }
        return instance;
    }

    /**
     * Dispatcher logic: Find the best elevator for a floor request
     */
    public void requestElevator(int floor, Direction direction) {
        Elevator bestElevator = findBestElevator(floor, direction);
        bestElevator.addFloor(floor);
    }

    private Elevator findBestElevator(int floor, Direction direction) {
        Elevator selected = null;
        int minDistance = Integer.MAX_VALUE;

        for (Elevator e : elevators) {
            int distance = calculateCost(e, floor, direction);
            if (distance < minDistance) {
                minDistance = distance;
                selected = e;
            }
        }
        return selected;
    }

    /**
     * Optimization Logic (Cost Function)
     */
    private int calculateCost(Elevator e, int floor, Direction direction) {
        int distance = Math.abs(e.currentFloor - floor);

        // Scenario 1: Elevator is IDLE - distance is the only factor
        if (e.currentDirection == Direction.IDLE) {
            return distance;
        }

        // Scenario 2: Elevator is moving TOWARDS the floor in the SAME direction
        if (e.currentDirection == direction) {
            if ((direction == Direction.UP && e.currentFloor < floor) ||
                    (direction == Direction.DOWN && e.currentFloor > floor)) {
                return distance;
            }
        }

        // Scenario 3: Elevator is moving away or in opposite direction
        // We add a "penalty" because it has to finish its current cycle first
        return distance + 100; // 100 is a symbolic penalty for building height
    }
}
