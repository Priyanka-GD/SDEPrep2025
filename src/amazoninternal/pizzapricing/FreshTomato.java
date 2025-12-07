package amazoninternal.pizzapricing;

public class FreshTomato extends Topping {
    public FreshTomato(Pizza pizza) {
        super(pizza);
    }

    @Override
    public String getDescription() {
        return pizza.getDescription() + ", Fresh Tomato";
    }

    @Override
    public int getCost() {
        return pizza.getCost() + 2;
    }
}
