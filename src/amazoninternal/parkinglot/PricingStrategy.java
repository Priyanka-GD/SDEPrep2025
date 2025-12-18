package amazoninternal.parkinglot;

import java.time.Instant;

public interface PricingStrategy {
    double price(VehicleSize size, Instant entryTime, Instant exitTime);
}
