package amazoninternal.lld.pizzapricing;

public abstract class Topping implements Pizza {
    protected Pizza pizza;

    public Topping(Pizza pizza) {
        this.pizza = pizza;
    }
}
