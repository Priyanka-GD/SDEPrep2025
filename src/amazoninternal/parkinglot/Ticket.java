package amazoninternal.parkinglot;

import java.time.Instant;
import java.util.Objects;

public class Ticket {
    private final String ticketId;
    private final Vehicle vehicle;
    private final Instant entryTime;

    public Ticket(String ticketId, Vehicle vehicle, Instant entryTime) {
        this.ticketId = Objects.requireNonNull(ticketId, "ticketId required");
        this.vehicle = Objects.requireNonNull(vehicle, "vehicle required");
        this.entryTime = Objects.requireNonNull(entryTime, "entryTime required");
    }

    public String getTicketId() {
        return ticketId;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public Instant getEntryTime() {
        return entryTime;
    }
}
