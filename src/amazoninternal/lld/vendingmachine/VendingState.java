package amazoninternal.lld.vendingmachine;

public interface VendingState {
    // Triggered when a user provides payment
    void handlePayment(VendingMachine machine, PaymentStrategy strategy);

    // Triggered when a user selects a product code (e.g., "A1")
    void selectProduct(VendingMachine machine, String productCode);

    // Triggered if the user wants their money back or stops mid-way
    void cancelTransaction(VendingMachine machine);

    // Triggered automatically after payment is successful
    void dispenseProduct(VendingMachine machine);
}