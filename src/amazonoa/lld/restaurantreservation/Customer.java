package amazonoa.lld.restaurantreservation;

public class Customer {
    private final String id;
    private final String name;
    private final String phoneNumber;
    private final int preferredPartySize;

    public Customer(String id, String name, String phoneNumber, int preferredPartySize) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.preferredPartySize = preferredPartySize;
    }

    // Getters
    public String getName() { return name; }
    public String getId() { return id; }
    public int getPreferredPartySize() { return preferredPartySize; }
}