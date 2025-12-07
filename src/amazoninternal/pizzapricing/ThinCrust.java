package amazoninternal.pizzapricing;

public class ThinCrust extends Base {
    public ThinCrust(Size size) {
        this.size = size;
    }

    @Override
    public String getDescription() {
        return "Thin Crust (" + size + ")";
    }

    @Override
    public int getCost() {
        return 5 + size.getPrice();
    }
}
