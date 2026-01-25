package amazoninternal.lld.elevatorcontrolsystem;

public interface ElevatorState {
    void handleFloorRequest(Elevator elevator, int floor);

    void openDoor(Elevator elevator);

    void closeDoor(Elevator elevator);

    void move(Elevator elevator);
}

class DoorOpeningState implements ElevatorState {
    @Override
    public void openDoor(Elevator elevator) {
        System.out.println("Elevator " + elevator.id + " opening doors at floor " + elevator.currentFloor);
        // In a real system, you'd start a timer here.
        // For this LLD, we immediately transition to Closing.
        elevator.setState(new DoorClosingState());
    }

    @Override
    public void handleFloorRequest(Elevator e, int f) {
        e.addStop(f);
    }

    @Override
    public void closeDoor(Elevator e) {
    }

    @Override
    public void move(Elevator e) {
    }
}

class DoorClosingState implements ElevatorState {
    @Override
    public void closeDoor(Elevator elevator) {
        System.out.println("Elevator " + elevator.id + " closing doors at floor " + elevator.currentFloor);

        // Decide next movement
        if (shouldContinueInCurrentDirection(elevator)) {
            elevator.setState(new MovingState());
            elevator.currentState.move(elevator); // Start moving to next stop
        } else if (shouldSwitchDirection(elevator)) {
            switchDirection(elevator);
            elevator.setState(new MovingState());
            elevator.currentState.move(elevator);
        } else {
            elevator.direction = Direction.IDLE;
            elevator.setState(new IdleState());
        }
    }

    private boolean shouldContinueInCurrentDirection(Elevator e) {
        return (e.direction == Direction.UP && !e.upStops.isEmpty()) ||
                (e.direction == Direction.DOWN && !e.downStops.isEmpty());
    }

    private boolean shouldSwitchDirection(Elevator e) {
        return (e.direction == Direction.UP && !e.downStops.isEmpty()) ||
                (e.direction == Direction.DOWN && !e.upStops.isEmpty());
    }

    private void switchDirection(Elevator e) {
        e.direction = (e.direction == Direction.UP) ? Direction.DOWN : Direction.UP;
    }

    @Override
    public void handleFloorRequest(Elevator e, int f) {
        e.addStop(f);
    }

    @Override
    public void openDoor(Elevator e) {
    }

    @Override
    public void move(Elevator e) {
    }
}// Updated MovingState

class MovingState implements ElevatorState {
    @Override
    public void move(Elevator elevator) {
        // Simple simulation: moving to the next stop in the heap
        if (elevator.direction == Direction.UP && !elevator.upStops.isEmpty()) {
            elevator.currentFloor = elevator.upStops.poll();
        } else if (elevator.direction == Direction.DOWN && !elevator.downStops.isEmpty()) {
            elevator.currentFloor = elevator.downStops.poll();
        }

        System.out.println("Elevator " + elevator.id + " arrived at " + elevator.currentFloor);
        elevator.setState(new DoorOpeningState());
        elevator.currentState.openDoor(elevator); // Trigger the chain
    }

    @Override
    public void handleFloorRequest(Elevator e, int f) {
        e.addStop(f);
    }

    @Override
    public void openDoor(Elevator e) {
    }

    @Override
    public void closeDoor(Elevator e) {
    }
}

class UnderMaintenanceState implements ElevatorState {
    @Override
    public void handleFloorRequest(Elevator elevator, int floor) {
        System.out.println("Elevator " + elevator.id + " is under maintenance. Request ignored.");
    }

    @Override
    public void openDoor(Elevator e) {
    }

    @Override
    public void closeDoor(Elevator e) {
    }

    @Override
    public void move(Elevator e) {
    }
}

class IdleState implements ElevatorState {

    @Override
    public void handleFloorRequest(Elevator elevator, int floor) {
        // 1. Add the stop to the appropriate heap
        elevator.addStop(floor);

        // 2. Determine initial direction based on the requested floor
        if (floor > elevator.currentFloor) {
            elevator.direction = Direction.UP;
        } else if (floor < elevator.currentFloor) {
            elevator.direction = Direction.DOWN;
        } else {
            // If the request is for the floor we are already on
            elevator.direction = Direction.IDLE;
            elevator.setState(new DoorOpeningState());
            elevator.currentState.openDoor(elevator);
            return;
        }

        // 3. Transition to MovingState and trigger movement
        System.out.println("Elevator " + elevator.id + " waking up. Heading " + elevator.direction);
        elevator.setState(new MovingState());
        elevator.currentState.move(elevator);
    }

    @Override
    public void openDoor(Elevator elevator) {
        // Even if IDLE, a user at the current floor might press the 'Open' button
        elevator.setState(new DoorOpeningState());
        elevator.currentState.openDoor(elevator);
    }

    @Override
    public void closeDoor(Elevator elevator) {
        // No-op: Doors are already closed in Idle
        System.out.println("Doors are already closed.");
    }

    @Override
    public void move(Elevator elevator) {
        // No-op: Cannot move without a destination
        System.out.println("Elevator is idle. Nowhere to go.");
    }
}