package amazoninternal.pizzapricing;

public class Small implements Size {
    @Override
    public String getSizeName() {
        return "Small Size";
    }

    @Override
    public int getBasePrice() {
        return 5;
    }
}
