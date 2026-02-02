package amazoninternal.lld.vendingmachine;

public class PaymentState implements VendingState {

    @Override
    public void handlePayment(VendingMachine machine, PaymentStrategy strategy) {
        // 1. Get the price of the selected item
        String code = machine.getSelectedItemCode();
        double price = machine.getInventory().get(code).getItem().amount;

        // 2. Use the strategy (DRY: We don't care if it's cash or card here)
        boolean success = strategy.processPayment(price);

        if (success) {
            System.out.println("Payment successful.");
            // 3. Transition to DispenseState
            machine.setCurrentState(new DispenseState());
            // Automatically trigger dispense
            machine.getCurrentState().dispenseProduct(machine);
        } else {
            System.out.println("Payment failed.");
        }
    }

    @Override
    public void selectProduct(VendingMachine machine, String productCode) {
        System.out.println("Product is already selected");
    }

    @Override
    public void cancelTransaction(VendingMachine machine) {
        System.out.println("Transaction cancelled. Returning to Idle.");
        machine.setSelectedItemCode(null);
        machine.setCurrentState(new IdleState());
    }

    @Override
    public void dispenseProduct(VendingMachine machine) {
        System.out.println("Yet to dispense the product");
    }

    // selectProduct and dispenseProduct would print "Invalid Action" or do nothing here
}