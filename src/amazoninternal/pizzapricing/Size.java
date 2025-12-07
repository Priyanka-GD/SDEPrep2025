package amazoninternal.pizzapricing;
public enum Size {
    SMALL(10),
    MEDIUM(15),
    LARGE(20);

    private final int price;

    Size(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}
