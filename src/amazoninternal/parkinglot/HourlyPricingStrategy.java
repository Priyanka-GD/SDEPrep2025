package amazoninternal.parkinglot;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.EnumMap;
import java.util.Map;

class HourlyPricingStrategy implements PricingStrategy {
    private final EnumMap<VehicleSize, Double> hourlyRate;

    public HourlyPricingStrategy(Map<VehicleSize, Double> hourlyRate) {
        this.hourlyRate = new EnumMap<>(VehicleSize.class);
        for (VehicleSize s : VehicleSize.values()) {
            Double r = hourlyRate.get(s);
            if (r == null || r < 0) throw new IllegalArgumentException("rate missing/invalid for " + s);
            this.hourlyRate.put(s, r);
        }
    }

    @Override
    public double price(VehicleSize size, Instant entryTime, Instant exitTime) {
        if (exitTime.isBefore(entryTime)) throw new IllegalArgumentException("exitTime before entryTime");
        long minutes = ChronoUnit.MINUTES.between(entryTime, exitTime);
        long hours = Math.max(1, (minutes + 59) / 60); // ceil to started hour
        return hours * hourlyRate.get(size);
    }
}