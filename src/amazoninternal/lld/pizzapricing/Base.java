package amazoninternal.lld.pizzapricing;


public interface  Base extends Pizza{
    String getBaseName();
    int getPrice(Size size);
}