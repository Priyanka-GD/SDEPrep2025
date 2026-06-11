package amazoninternal.lld.lockermanagement;

public class Locker {
    Size size;
    String lockerId;
    boolean isAvailable;

    public Locker(Size size, String lockerId) {
        this.size = size;
        this.lockerId = lockerId;
        this.isAvailable = true;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public String getLockerId() {
        return lockerId;
    }

    public void setLockerId(String lockerId) {
        this.lockerId = lockerId;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}
