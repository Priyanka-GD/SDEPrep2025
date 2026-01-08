package amazoninternal.lld.amazonlocker;

public class Compartment {
    int compartmentId;
    Size size;
    boolean isOccupied;

    public Compartment(int compartmentId, Size size) {
        this.compartmentId = compartmentId;
        this.size = size;
    }

    public int getCompartmentId() {
        return compartmentId;
    }

    public Size getSize() {
        return size;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void markOccupied() {
        isOccupied = true;
    }

    public void markFree() {
        isOccupied = false;
    }
}
