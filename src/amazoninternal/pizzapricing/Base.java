package amazoninternal.pizzapricing;


public interface  Base extends Pizza{
    String getBaseName();
    int getPrice(Size size);
}