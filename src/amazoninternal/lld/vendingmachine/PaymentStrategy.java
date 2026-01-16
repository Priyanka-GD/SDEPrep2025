package amazoninternal.lld.vendingmachine;

public interface PaymentStrategy {
    // Returns true if payment was successful
    boolean processPayment(double amount);
}

