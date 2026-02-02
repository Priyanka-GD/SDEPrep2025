package amazoninternal.lld.vendingmachine;

public class IdleState implements VendingState {

    @Override
    public void selectProduct(VendingMachine machine, String productCode) {
        // 1. Logic: Check if product exists/available
        if (machine.getAvailability(productCode)) {
            // 2. Data update: Save the choice in the machine context
            machine.setSelectedItemCode(productCode);
            // 3. State Transition: Move to the next state
            System.out.println("Product " + productCode + " selected.");
            machine.setCurrentState(machine.getCurrentState());
        } else {
            System.out.println("Product out of stock or invalid code.");
        }
    }

    // KISS: Other methods are "Invalid" in this state
    @Override
    public void handlePayment(VendingMachine machine, PaymentStrategy strategy) {
        System.out.println("Please select a product first.");
    }

    @Override
    public void cancelTransaction(VendingMachine machine) {
        System.out.println("Nothing to cancel.");
    }

    @Override
    public void dispenseProduct(VendingMachine machine) {
        System.out.println("Select product and pay first.");
    }
}