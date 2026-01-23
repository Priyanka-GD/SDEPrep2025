package amazoninternal.lld.elevatormanagement;

public class IdleState implements ElevatorState {
    @Override
    public void pressFloorNumber(Elevator e, int floorNo) {
        e.addFloor(floorNo);
        // Transition to Moving because we now have a destination
        e.setState(new MovingState());
    }

    @Override
    public void openDoor(Elevator e) {
        // User can open doors while IDLE at a floor
        e.setState(new DoorOpenState());
    }

    @Override
    public void closeDoor(Elevator e) {
        // Already closed; do nothing
    }

    @Override
    public void reachFloor(Elevator e) {
        // Logic error: cannot "reach" a floor if not moving
    }
}
