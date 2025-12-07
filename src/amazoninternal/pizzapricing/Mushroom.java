package amazoninternal.pizzapricing;

public class Mushroom extends Topping {
    public Mushroom(Pizza pizza) {
        super(pizza);
    }

    @Override
    public String getDescription() {
        return pizza.getDescription() + ", Mushroom";
    }

    @Override
    public int getCost() {
        return pizza.getCost() + 3;
    }
}
