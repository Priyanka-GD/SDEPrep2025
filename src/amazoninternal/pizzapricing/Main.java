package amazoninternal.pizzapricing;
public class Main {
    public static void main(String[] args) {

        Pizza pizza = new ThinCrust(Size.SMALL);

        pizza = new FreshTomato(pizza);
        pizza = new Mushroom(pizza);

        System.out.println(pizza.getDescription());
        System.out.println("Total Price: $" + pizza.getCost());
    }
}
