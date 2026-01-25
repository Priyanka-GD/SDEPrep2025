package amazoninternal.lld.elevatorcontrolsystem;

import java.util.*;

public class Elevator {
    int id;
    int currentFloor;
    int capacity;
    Direction direction;
    ElevatorState currentState;

    // Heaps for the LOOK Algorithm
    PriorityQueue<Integer> upStops;   // Min-Heap
    PriorityQueue<Integer> downStops; // Max-Heap

    public Elevator(int id) {
        this.id = id;
        this.currentFloor = 0;
        this.direction = Direction.IDLE;
        this.upStops = new PriorityQueue<>();
        this.downStops = new PriorityQueue<>(Collections.reverseOrder());
        this.currentState = new IdleState(); // Initial state
    }

    public void setState(ElevatorState state) {
        this.currentState = state;
    }

    public void addStop(int floor) {
        if (floor > currentFloor) upStops.add(floor);
        else if (floor < currentFloor) downStops.add(floor);
    }
}