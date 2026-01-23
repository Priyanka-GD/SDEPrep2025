package amazoninternal.lld.elevatormanagement;

public class MovingState implements ElevatorState {
    @Override
    public void pressFloorNumber(Elevator e, int floorNo) {
        // Just add to the queue; the elevator continues its current path
        e.addFloor(floorNo);
    }

    @Override
    public void openDoor(Elevator e) {
        // Safety constraint: Doors cannot open while moving
        System.out.println("Cannot open door while moving!");
    }

    @Override
    public void closeDoor(Elevator e) {
        // Already closed
    }

    @Override
    public void reachFloor(Elevator e) {
        int target = (e.currentDirection == Direction.UP) ? e.upQueue.peek() : e.downQueue.peek();
        if (e.currentFloor == target) {
            // Arrived! Remove from queue and open doors
            if (e.currentDirection == Direction.UP)
                e.upQueue.poll();
            else
                e.downQueue.poll();
            e.setState(new DoorOpenState());
        }
    }
}
