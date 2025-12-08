package amazoninternal.pizzapricing;

public class ThickCrust implements Base, Pizza {
    private Size size;

    public ThickCrust(Size size) {
        this.size = size;
    }

    @Override
    public String getBaseName() {
        return "Thick Crust";
    }

    @Override
    public int getPrice(Size size) {
        return 9 + size.getBasePrice();
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
