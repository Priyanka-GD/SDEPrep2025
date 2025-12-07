package amazonoa.lld;

public class Customer {
    String name;
    String shippingAddress;
    String userId;
    int latitude;
    int longitude;

    public Customer(String name, String shippingAddress, String userId, int latitude, int longitude) {
        this.name = name;
        this.shippingAddress = shippingAddress;
        this.userId = userId;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
