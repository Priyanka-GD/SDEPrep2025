package amazoninternal.lld.elevatormanagement;

public class DoorOpenState implements ElevatorState {
    @Override
    public void pressFloorNumber(Elevator e, int floorNo) {
        // Still accepting requests while doors are open
        e.addFloor(floorNo);
    }

    @Override
    public void openDoor(Elevator e) {
        // Already open; perhaps reset the door-close timer
    }

    @Override
    public void closeDoor(Elevator e) {
        // Transition logic: Decide where to go next
        if (!e.upQueue.isEmpty() || !e.downQueue.isEmpty()) {
            // Determine if we need to switch direction
            if (e.currentDirection == Direction.UP && e.upQueue.isEmpty()) {
                e.currentDirection = Direction.DOWN;
            } else if (e.currentDirection == Direction.DOWN && e.downQueue.isEmpty()) {
                e.currentDirection = Direction.UP;
            }
            e.setState(new MovingState());
        } else {
            e.currentDirection = Direction.IDLE;
            e.setState(new IdleState());
        }
    }

    @Override
    public void reachFloor(Elevator e) {
        // Logic error: already at a floor
    }
}
