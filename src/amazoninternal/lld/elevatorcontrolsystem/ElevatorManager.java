package amazoninternal.lld.elevatorcontrolsystem;

import java.util.ArrayList;
import java.util.List;

public class ElevatorManager {
    List<Elevator> elevators;

    public ElevatorManager(int count) {
        elevators = new ArrayList<>();
        for (int i = 0; i < count; i++)
            elevators.add(new Elevator(i));
    }

    public void requestElevator(int floor, Direction dir) {
        Elevator bestElevator = null;
        int minDistance = Integer.MAX_VALUE;

        for (Elevator e : elevators) {
            int distance = Math.abs(e.currentFloor - floor);

            // Logic: Pick if Idle OR moving in same direction and hasn't passed floor
            if (isEligible(e, floor, dir) && distance < minDistance) {
                minDistance = distance;
                bestElevator = e;
            }
        }

        if (bestElevator != null) {
            bestElevator.currentState.handleFloorRequest(bestElevator, floor);
        }
    }

    private boolean isEligible(Elevator e, int floor, Direction dir) {
        if (e.direction == Direction.IDLE)
            return true;
        if (e.direction == Direction.UP && dir == Direction.UP && e.currentFloor <= floor)
            return true;
        return e.direction == Direction.DOWN && dir == Direction.DOWN && e.currentFloor >= floor;
    }
}
