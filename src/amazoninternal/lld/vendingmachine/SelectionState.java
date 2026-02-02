package amazoninternal.lld.vendingmachine;

public class SelectionState implements VendingState {

    @Override
    public void selectProduct(VendingMachine machine, String productCode) {
        // KISS: Just update the selection if they change their mind before paying
        if (machine.getAvailability(productCode)) {
            machine.setSelectedItemCode(productCode);
            System.out.println("Selection updated to: " + productCode);
        } else {
            System.out.println("Product " + productCode + " is unavailable.");
        }
    }

    @Override
    public void handlePayment(VendingMachine machine, PaymentStrategy strategy) {
        // This is the "Success Path"
        // Transition to PaymentState and immediately trigger the payment logic
        System.out.println("Proceeding to payment...");
        machine.setCurrentState(new PaymentState());

        // Delegate the actual payment work to the new state
        machine.getCurrentState().handlePayment(machine, strategy);
    }

    @Override
    public void cancelTransaction(VendingMachine machine) {
        System.out.println("Selection cancelled. Returning to Idle State.");
        machine.setSelectedItemCode(null);
        machine.setCurrentState(new IdleState());
    }

    @Override
    public void dispenseProduct(VendingMachine machine) {
        System.out.println("Cannot dispense. Payment required first.");
    }
}