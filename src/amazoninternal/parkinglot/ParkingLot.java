package amazoninternal.parkinglot;

import java.time.Clock;
import java.time.Instant;
import java.util.*;

public class ParkingLot {
    private final EnumMap<VehicleSize, Integer> available;
    private final Map<String, Ticket> openTickets = new HashMap<>();
    private final TicketFactory ticketFactory;
    private final PricingStrategy pricingStrategy;
    private final Clock clock;

    public ParkingLot(int smallSpaces, int mediumSpaces, int largeSpaces,
                      TicketFactory ticketFactory,
                      PricingStrategy pricingStrategy,
                      Clock clock) {
        if (smallSpaces < 0 || mediumSpaces < 0 || largeSpaces < 0) throw new IllegalArgumentException("spaces must be >= 0");
        this.available = new EnumMap<>(VehicleSize.class);
        this.available.put(VehicleSize.SMALL, smallSpaces);
        this.available.put(VehicleSize.MEDIUM, mediumSpaces);
        this.available.put(VehicleSize.LARGE, largeSpaces);

        this.ticketFactory = Objects.requireNonNull(ticketFactory, "ticketFactory required");
        this.pricingStrategy = Objects.requireNonNull(pricingStrategy, "pricingStrategy required");
        this.clock = Objects.requireNonNull(clock, "clock required");
    }

    public synchronized Optional<Ticket> entry(Vehicle vehicle) {
        VehicleSize size = vehicle.getSize();
        int free = available.get(size);

        if (free <= 0) return Optional.empty();

        Ticket ticket = ticketFactory.create(vehicle);
        openTickets.put(ticket.getTicketId(), ticket);
        available.put(size, free - 1);

        return Optional.of(ticket);
    }

    public synchronized double exit(String ticketId) {
        Ticket ticket = openTickets.remove(ticketId);
        if (ticket == null) throw new NoSuchElementException("No open ticket for id: " + ticketId);

        Instant exitTime = Instant.now(clock);
        double amount = pricingStrategy.price(ticket.getVehicle().getSize(), ticket.getEntryTime(), exitTime);

        VehicleSize size = ticket.getVehicle().getSize();
        available.put(size, available.get(size) + 1);

        return amount;
    }

    public synchronized int getAvailable(VehicleSize size) {
        return available.get(size);
    }

    public synchronized int getOpenTicketCount() {
        return openTickets.size();
    }
}