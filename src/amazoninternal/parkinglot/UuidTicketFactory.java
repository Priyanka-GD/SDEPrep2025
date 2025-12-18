package amazoninternal.parkinglot;

import java.time.Clock;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

final class UuidTicketFactory implements TicketFactory {
    private final Clock clock;

    public UuidTicketFactory(Clock clock) {
        this.clock = Objects.requireNonNull(clock, "clock required");
    }

    @Override
    public Ticket create(Vehicle vehicle) {
        return new Ticket(UUID.randomUUID().toString(), vehicle, Instant.now(clock));
    }
}