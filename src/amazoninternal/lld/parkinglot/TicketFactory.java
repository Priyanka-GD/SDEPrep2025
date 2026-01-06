package amazoninternal.lld.parkinglot;

public interface TicketFactory {
    Ticket create(Vehicle vehicle);
}
