package amazoninternal.pizzapricing;

public class ThinCrust implements Base, Pizza {

    private final Size size;

    public ThinCrust(Size size) {
        this.size = size;
    }

    @Override
    public String getBaseName() {
        return "Thin Crust";
    }

    @Override
    public int getPrice(Size size) {
        return size.getBasePrice() + 5;
    }

    @Override
    public String getDescription() {
        return getBaseName() + " (" + size.getSizeName() + ")";
    }

    @Override
    public int getCost() {
        return getPrice(size);
    }
}
