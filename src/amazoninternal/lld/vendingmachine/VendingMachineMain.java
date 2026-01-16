package amazoninternal.lld.vendingmachine;

import java.util.HashMap;
import java.util.Map;

public class VendingMachineMain {
    public static void main(String[] args) {
        // 1. Setup Inventory
        Item coke = new Item("Coke", 1.50);
        Item pepsi = new Item("Pepsi", 1.25);

        Map<String, Slot> inventory = new HashMap<>();
        inventory.put("A1", new Slot("A1", coke, 5));
        inventory.put("B1", new Slot("B1", pepsi, 3));

        // 2. Initialize the Machine
        VendingMachine machine = new VendingMachine(inventory);

        System.out.println("--- Vending Machine Initialized ---");

        // 3. User Scenario: Buy a Coke with a Credit Card
        System.out.println("\nScenario 1: Successful Purchase");

        // Step 1: Select
        machine.getCurrentState().selectProduct(machine, "A1");

        // Step 2: Pay
        // Notice how we pass the Strategy here (DRY)
        PaymentStrategy cardPayment = new CardPaymentStrategy("1234-5678-9012-3456");
        machine.getCurrentState().handlePayment(machine, cardPayment);

        // 4. User Scenario: Try to buy without selecting (Testing KISS logic)
        System.out.println("\nScenario 2: Invalid Action Test");
        machine.getCurrentState().handlePayment(machine, cardPayment);
    }
}