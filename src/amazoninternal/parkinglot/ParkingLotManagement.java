package amazoninternal.parkinglot;

import java.time.Clock;
import java.util.Map;
import java.util.Optional;

public class ParkingLotManagement {
    public static void main(String[] args) throws InterruptedException {
        Clock clock = Clock.systemUTC();

        TicketFactory ticketFactory = new UuidTicketFactory(clock);

        Map<VehicleSize, Double> rates = Map.of(
                VehicleSize.SMALL, 5.0,
                VehicleSize.MEDIUM, 7.0,
                VehicleSize.LARGE, 10.0
        );
        PricingStrategy pricing = new HourlyPricingStrategy(rates);

        ParkingLot lot = new ParkingLot(5, 2, 5, ticketFactory, pricing, clock);

        Vehicle v1 = new Vehicle("ICV890", VehicleSize.SMALL);

        Optional<Ticket> maybeTicket = lot.entry(v1);
        if (maybeTicket.isEmpty()) {
            System.out.println("No space available for " + v1.getSize());
            return;
        }

        Ticket t = maybeTicket.get();
        System.out.println("Issued ticket: " + t.getTicketId());
        System.out.println("Available SMALL: " + lot.getAvailable(VehicleSize.SMALL));

        // simulate time passing
        Thread.sleep(1500);

        double amount = lot.exit(t.getTicketId());
        System.out.println("Amount due: $" + amount);
        System.out.println("Available SMALL: " + lot.getAvailable(VehicleSize.SMALL));
    }

}
