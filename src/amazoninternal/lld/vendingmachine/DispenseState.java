package amazoninternal.lld.vendingmachine;

public class DispenseState implements VendingState {

    @Override
    public void dispenseProduct(VendingMachine machine) {
        String code = machine.getSelectedItemCode();
        Slot slot = machine.getInventory().get(code);

        // 1. Perform the physical action (Updating the data)
        slot.dispenseItem(1);
        System.out.println("Dispensing: " + slot.getItem().name);

        // 2. Cleanup the transaction data
        machine.setSelectedItemCode(null);

        // 3. Automatically transition back to Idle for the next customer
        System.out.println("Transaction complete. Returning to Idle State.");
        machine.setCurrentState(new IdleState());
    }

    @Override
    public void handlePayment(VendingMachine machine, PaymentStrategy strategy) {
        System.out.println("Already paid. Currently dispensing product.");
    }

    @Override
    public void selectProduct(VendingMachine machine, String productCode) {
        System.out.println("Please wait, currently dispensing previous selection.");
    }

    @Override
    public void cancelTransaction(VendingMachine machine) {
        // KISS: Usually, once you reach dispense state, the money is gone and the item is moving.
        // You could implement a "Too late to cancel" policy here.
        System.out.println("Cannot cancel. Item is already being dispensed.");
    }
}