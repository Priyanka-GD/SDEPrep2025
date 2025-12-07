package amazoninternal.pizzapricing;

public class ThickCrust extends Base {
    public ThickCrust(Size size) {
        this.size = size;
    }

    @Override
    public String getDescription() {
        return "Thick Crust (" + size + ")";
    }

    @Override
    public int getCost() {
        return 7 + size.getPrice();
    }
}
