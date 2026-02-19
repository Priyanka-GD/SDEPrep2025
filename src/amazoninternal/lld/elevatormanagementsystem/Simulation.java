package amazoninternal.lld.elevatormanagementsystem;

public class Simulation {
    public static void main(String[] args) throws InterruptedException {
        // Initialize: 3 elevators, 10 floors
        ElevatorController controller = new ElevatorController(3, 10);

        System.out.println("--- Starting Simulation ---");

        // Use Case 1: Person on Floor 3 wants to go UP
        System.out.println("[External] Request: Floor 3, Direction UP");
        controller.requestElevator(3, Direction.UP);

        // Use Case 2: Person on Floor 8 wants to go DOWN
        System.out.println("[External] Request: Floor 8, Direction DOWN");
        controller.requestElevator(8, Direction.DOWN);

        // Run the simulation for 15 "ticks"
        for (int i = 0; i < 15; i++) {
            System.out.println("\n--- Step " + i + " ---");
            controller.step();
            printState();

            // Use Case 3: Once an elevator reaches Floor 3, simulate someone pressing Floor 5
            for (Elevator e : ElevatorController.elevators) {
                if (e.currentFloor == 3 && e.direction == Direction.IDLE) {
                    System.out.println("[Internal] Passenger inside Elevator reached Floor 3, requests Floor 5");
                    e.addRequest(5, RequestType.DESTINATION);
                }
            }

            Thread.sleep(500); // Slow down so we can read the output
        }
    }

    private static void printState() {
        for (int i = 0; i < ElevatorController.elevators.size(); i++) {
            Elevator e = ElevatorController.elevators.get(i);
            System.out.printf("Elevator %d: Floor %d | Status: %s | Active Requests: %d%n",
                    i, e.currentFloor, e.direction, e.requestSet.size());
        }
    }
}