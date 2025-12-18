package amazoninternal.parkinglot;

public interface TicketFactory {
    Ticket create(Vehicle vehicle);
}
