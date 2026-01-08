package amazoninternal.lld.amazonlocker;
import java.util.Scanner;

public class LockerManager {

    private final Locker locker;
    private final Scanner scanner;

    public LockerManager() {
        this.locker = new Locker();
        this.scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        LockerManager manager = new LockerManager();

        // Sample setup (you can also create via menu)
        manager.seedCompartments();

        manager.run();
    }

    private void run() {
        while (true) {
            printMenu();
            int choice = readInt("Enter choice: ");

            try {
                switch (choice) {
                    case 1 -> createCompartmentFlow();
                    case 2 -> depositFlow();
                    case 3 -> pickupFlow();
                    case 4 -> listCompartmentsFlow();
                    case 0 -> {
                        System.out.println("Exiting...");
                        return;
                    }
                    default -> System.out.println("Invalid choice. Try again.");
                }
            } catch (Error e) { // you used `throw new Error(...)` in Locker
                System.out.println("ERROR: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }

            System.out.println();
        }
    }

    private void printMenu() {
        System.out.println("===== Amazon Locker Manager =====");
        System.out.println("1. Add Compartment");
        System.out.println("2. Deposit Package");
        System.out.println("3. Pick Up Package");
        System.out.println("4. List Compartments");
        System.out.println("0. Exit");
    }

    private void createCompartmentFlow() {
        int id = readInt("Compartment ID: ");
        Size size = readSize("Size (SMALL/MEDIUM/LARGE): ");

        Compartment c = new Compartment(id, size);
        locker.addCompartment(c);

        System.out.println("Added compartment: id=" + id + ", size=" + size);
    }

    private void depositFlow() {
        Size size = readSize("Package size to deposit (SMALL/MEDIUM/LARGE): ");

        TokenRecord record = locker.depositPackage(size);

        System.out.println("Deposited successfully!");
        System.out.println("Compartment ID: " + record.compartmentId);
        System.out.println("Pickup Token  : " + record.token);
    }

    private void pickupFlow() {
        System.out.print("Enter pickup token: ");
        String token = scanner.nextLine().trim();

        int compartmentId = locker.pickUp(token);
        System.out.println("Pickup successful. Open compartment: " + compartmentId);
    }

    private void listCompartmentsFlow() {
        System.out.println("Compartments:");
        if (locker.getCompartmentList().isEmpty()) {
            System.out.println("(none)");
            return;
        }

        for (Compartment c : locker.getCompartmentList()) {
            System.out.println(
                    "id=" + c.getCompartmentId() +
                            ", size=" + c.getSize() +
                            ", occupied=" + c.isOccupied()
            );
        }
    }

    private Size readSize(String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = scanner.nextLine().trim().toUpperCase();
            try {
                return Size.valueOf(s);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid size. Please type SMALL, MEDIUM, or LARGE.");
            }
        }
    }

    private int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = scanner.nextLine().trim();
            try {
                return Integer.parseInt(s);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid integer.");
            }
        }
    }

    private void seedCompartments() {
        // Example compartments to start with
        locker.addCompartment(new Compartment(1, Size.SMALL));
        locker.addCompartment(new Compartment(2, Size.SMALL));
        locker.addCompartment(new Compartment(3, Size.MEDIUM));
        locker.addCompartment(new Compartment(4, Size.LARGE));
    }
}
