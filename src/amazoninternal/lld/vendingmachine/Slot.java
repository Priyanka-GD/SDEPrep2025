package amazoninternal.lld.vendingmachine;

public class Slot {
    private Item item;
    private int quantity;
    private final String code;

    public Slot(String code, Item item, int quantity) {
        this.code = code;
        this.item = item;
        this.quantity = quantity;
    }

    // KISS: A simple helper to check availability
    public boolean isAvailable(int count) {
        return quantity >= count;
    }

    // DRY: One place to handle the reduction of stock
    public void dispenseItem(int count) {
        if (quantity >= count) {
            quantity -= count;
        }
    }

    public Item getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getCode() {
        return code;
    }
// Getters for item price and name...
}