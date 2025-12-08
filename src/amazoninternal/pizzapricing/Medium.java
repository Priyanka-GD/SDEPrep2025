package amazoninternal.pizzapricing;

public class Medium implements Size {
    @Override
    public String getSizeName() {
        return "Medium Size";
    }

    @Override
    public int getBasePrice() {
        return 10;
    }
}
