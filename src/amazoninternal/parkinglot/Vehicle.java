package amazoninternal.parkinglot;

import java.util.Objects;

public class Vehicle {
    private final String licensePlate;
    private final VehicleSize size;

    public Vehicle(String licensePlate, VehicleSize size) {
        if (licensePlate == null || licensePlate.isBlank())
            throw new IllegalArgumentException("licensePlate required");
        this.licensePlate = licensePlate;
        this.size = Objects.requireNonNull(size, "size required");
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public VehicleSize getSize() {
        return size;
    }
}
