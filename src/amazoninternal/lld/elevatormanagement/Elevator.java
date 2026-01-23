package amazoninternal.lld.elevatormanagement;

import java.util.Collections;
import java.util.PriorityQueue;

public class Elevator {
    int currentFloor;
    int capacity;
    Direction currentDirection;

    PriorityQueue<Integer> upQueue;
    PriorityQueue<Integer> downQueue;

    ElevatorState state;

    public Elevator(int capacity) {
        this.capacity = capacity;
        this.currentFloor = 0; // Starting at lobby
        this.currentDirection = Direction.IDLE;
        this.upQueue = new PriorityQueue<>();
        this.downQueue = new PriorityQueue<>(Collections.reverseOrder());
        this.state = new IdleState();
    }

    public void setDirection(Direction direction) {
        this.currentDirection = direction;
    }

    public void setFloorNo(int floorNo) {
        this.currentFloor = floorNo;
    }

    public void addFloor(int floor) {
        if (floor == currentFloor)
            return; // Optimization: Ignore if already there

        // 1. Always add to the correct queue first
        if (floor > currentFloor) {
            upQueue.offer(floor);
        } else {
            downQueue.offer(floor);
        }

        // 2. Only update direction if it was IDLE
        if (this.currentDirection == Direction.IDLE) {
            this.currentDirection = (floor > currentFloor) ? Direction.UP : Direction.DOWN;
            this.setState(new MovingState());
        }
    }

    public void setState(ElevatorState state){
        this.state = state;
    }
    public void onFloorReached() {
        // The Elevator (Context) doesn't know what to do,
        // so it asks the State implementation.
        state.reachFloor(this);
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public Direction getCurrentDirection() {
        return currentDirection;
    }

    public ElevatorState getCurrentState() {
        return state;
    }
    public void closeDoor() {
        // The context delegates the action to the current state object
        state.closeDoor(this);
    }
}

