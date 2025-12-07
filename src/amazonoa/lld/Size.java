package amazonoa.lld;

public enum Size {
    SMALL(1),
    MEDIUM(2),
    LARGE(3);

    private final int value;

    Size(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
