package amazoninternal.lld.planvalidation;

public class Event {
    int time;
    Operation operation;
    int pallet;
    String location;

    public Event(int time, Operation operation, int pallet, String location) {
        this.time = time;
        this.operation = operation;
        this.pallet = pallet;
        this.location = location;
    }

    public int getTime() {
        return time;
    }

    public Operation isPickUp() {
        return operation;
    }

    public int getPallet() {
        return pallet;
    }

    public String getLocation() {
        return location;
    }
}