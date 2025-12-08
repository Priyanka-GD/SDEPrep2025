package amazoninternal.pizzapricing;
public class Main {
    public static void main(String[] args) {
        // Step 1: Choose size
        Size size = new Medium();
        // Step 2: Choose pizza base
        Pizza pizza = new ThinCrust(size);
        // Step 3: Add toppings (Decorator)
        pizza = new Mushroom(pizza);
        pizza = new FreshTomato(pizza);

        // Step 4: Final Output
        System.out.println("Order Summary:");
        System.out.println(pizza.getDescription());
        System.out.println("Total Price: $" + pizza.getCost());
    }
}
