package amazoninternal.lld.elevatormanagement;

public interface ElevatorState {
    void pressFloorNumber(Elevator e, int floorNo);
    void openDoor(Elevator e);
    void closeDoor(Elevator e);
    void reachFloor(Elevator e);
}

