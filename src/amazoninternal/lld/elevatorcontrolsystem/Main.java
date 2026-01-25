package amazoninternal.lld.elevatorcontrolsystem;

public class Main {
    public static void main(String[] args) {
        // 1. Initialize the Manager with 3 elevators
        ElevatorManager manager = new ElevatorManager(3);

        System.out.println("--- Starting Elevator Simulation ---");

        // 2. Scenario: Someone on Floor 0 wants to go UP
        // This should trigger an Idle elevator to pick them up
        System.out.println("\n[Request] Floor 0: Press UP");
        manager.requestElevator(0, Direction.UP);

        // 3. Scenario: Someone inside an elevator wants to go to Floor 5
        // Let's assume Elevator 0 was picked.
        Elevator elevator0 = manager.elevators.get(0);
        System.out.println("\n[Request] Inside Elevator 0: Press Floor 5");
        elevator0.currentState.handleFloorRequest(elevator0, 5);

        // 4. Scenario: Someone on Floor 3 wants to go UP
        // The manager should ideally pick Elevator 0 since it's heading UP to 5
        System.out.println("\n[Request] Floor 3: Press UP");
        manager.requestElevator(3, Direction.UP);

        // 5. Trigger the movement lifecycle
        // In a real system, this would be handled by a clock or thread
        System.out.println("\n--- Processing Elevator 0 Movement ---");

        // Elevator 0 is at Floor 0 (from step 2), now moves to Floor 3
        elevator0.currentState.move(elevator0);

        // After reaching 3, it would open/close doors.
        // Then move() is called again to reach Floor 5.
        elevator0.currentState.closeDoor(elevator0);

        // 6. Maintenance check
        System.out.println("\n[Admin] Setting Elevator 1 to Maintenance");
        manager.elevators.get(1).setState(new UnderMaintenanceState());
        manager.requestElevator(10, Direction.DOWN); // Should ignore Elevator 1
    }
}
