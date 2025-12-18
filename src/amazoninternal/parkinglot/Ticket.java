package amazoninternal.parkinglot;

public class Ticket {
    String ticketId;
    Vehicle vehicle;
    String entryTime;

    public Ticket(String ticketId, Vehicle vehicle, String entryTime) {
        this.ticketId = ticketId;
        this.vehicle = vehicle;
        this.entryTime = entryTime;
    }
}
