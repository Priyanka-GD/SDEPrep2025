package amazoninternal.lld.vendingmachine;

import java.util.Map;

public class VendingMachine {
    // 1. All possible states (Pre-instantiated for efficiency)
    private VendingState idleState;
    private VendingState selectionState;
    private VendingState paymentState;
    private VendingState dispenseState;
    public VendingState getSelectionState() { return selectionState; }
    public VendingState getPaymentState() { return paymentState; }
    public VendingState getIdleState() { return idleState; }
    public VendingState getDispenseState() { return dispenseState; }
    // 2. The dynamic state
    private VendingState currentState;

    // 3. Data Storage
    private Map<String, Slot> inventory;
    private int quantity;
    private String selectedItemCode;

    public VendingMachine(Map<String, Slot> inventory) {
        this.inventory = inventory;

        // Initialize states
        this.idleState = new IdleState();
        this.selectionState = new SelectionState();
        this.paymentState = new PaymentState();
        this.dispenseState = new DispenseState();

        // Start in the Idle state
        this.currentState = idleState;
    }

    public boolean getAvailability(String code) {
        Slot slot = inventory.get(code);
        return slot != null && slot.isAvailable(1); // Assume 1 for standard purchase
    }

    public void setCurrentState(VendingState newState) {
        this.currentState = newState;
    }

    public void setSelectedItemCode(String productCode) {
        this.selectedItemCode = productCode;
    }

    public String getSelectedItemCode() {
        return selectedItemCode;
    }

    public Map<String, Slot> getInventory() {
        return inventory;
    }

    public VendingState getCurrentState() {
        return currentState;
    }
}
