package amazoninternal.lld.elevatormanagement;

public class Main {
    public static void main(String[] args) {
        // 1. Initialize the Manager with 2 Elevators, each with capacity 10
        ElevatorManager manager = ElevatorManager.getInstance(2, 10);

        // Let's manually set initial positions for demonstration
        Elevator e1 = manager.getElevators().get(0);
        Elevator e2 = manager.getElevators().get(1);

        e1.setFloorNo(0); // Elevator 1 is at Lobby
        e2.setFloorNo(8); // Elevator 2 is at Floor 8

        System.out.println("--- Elevator Status ---");
        System.out.println("Elevator 1 at floor: " + e1.getCurrentFloor());
        System.out.println("Elevator 2 at floor: " + e2.getCurrentFloor());

        // 2. User at Floor 3 wants to go UP
        System.out.println("\n[Action]: User at Floor 3 presses UP button.");
        manager.requestElevator(3, Direction.UP);

        // Scenario: Multiple floors in the same direction
        System.out.println("\n[Action]: User wants floors 5, 2, and 8.");
        e1.addFloor(5);
        e1.addFloor(2);
        e1.addFloor(8);

        // Logic check: E1 is 3 floors away (Idle), E2 is 5 floors away (Idle).
        // E1 should be selected.

        // 3. Simulate the Elevator Movement
        // In a real system, this would be handled by a Thread or a Clock loop
        simulateElevatorProcess(e1);
    }

    private static void simulateElevatorProcess(Elevator e) {
        System.out.println("\n--- Simulation Started for Selected Elevator ---");

        // While elevator has stops to make
        while (e.getCurrentDirection() != Direction.IDLE) {
            System.out.println("Current Position: Floor " + e.getCurrentFloor() + " | State: " + e.getCurrentState().getClass().getSimpleName());

            // Trigger a floor reach (moving it closer to target)
            if (e.getCurrentDirection() == Direction.UP) {
                e.setFloorNo(e.getCurrentFloor() + 1);
            } else if (e.getCurrentDirection() == Direction.DOWN) {
                e.setFloorNo(e.getCurrentFloor() - 1);
            }

            // Notify the state that a floor was reached
            e.onFloorReached();

            // If the state transitioned to DoorOpenState, simulate a door cycle
            if (e.getCurrentState() instanceof DoorOpenState) {
                System.out.println("Arrived at Floor " + e.getCurrentFloor() + ". Doors Opening...");
                e.closeDoor(); // This triggers the logic to check for next floors or go IDLE
                System.out.println("Doors Closed.");
            }
        }

        System.out.println("Final Status: Elevator is now " + e.getCurrentDirection() + " at Floor " + e.getCurrentFloor());

    }
}