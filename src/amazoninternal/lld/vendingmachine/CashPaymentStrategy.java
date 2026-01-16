package amazoninternal.lld.vendingmachine;

public class CashPaymentStrategy implements PaymentStrategy {
    @Override
    public boolean processPayment(double amount) {
        System.out.println("Validating cash: $" + amount);
        return true;
    }
}
