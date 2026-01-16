package amazoninternal.lld.vendingmachine;

public class CardPaymentStrategy implements PaymentStrategy {
    private String cardNumber;

    public CardPaymentStrategy(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public boolean processPayment(double amount) {
        System.out.println("Processing card " + cardNumber + " for $" + amount);
        // Add actual bank API logic here
        return true;
    }
}
