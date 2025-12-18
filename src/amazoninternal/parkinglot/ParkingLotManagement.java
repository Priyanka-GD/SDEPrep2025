package amazoninternal.parkinglot;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ParkingLotManagement {
    static Map<String, Ticket> mapOfOpenTickets;
    static int[] availableSpaces; //small, medium, large

    public ParkingLotManagement(int smallSpaces, int mediumSpaces, int largeSpaces) {
        mapOfOpenTickets = new HashMap<>();
        availableSpaces = new int[]{smallSpaces, mediumSpaces, largeSpaces};
    }

    public static void main(String args[]) {
        ParkingLotManagement parkingLotManagement = new ParkingLotManagement(5, 2, 5);
        Vehicle vehicle = new Vehicle("ICV890", "SMALL");
        int vehicleSize = getVehicleType(vehicle.vehicleSize);
        if (availableSpaces[vehicleSize] > 0) {
            Ticket ticket = entry(vehicle);
        }
    }

    public static double exit(String ticketId) {
        Ticket ticket = mapOfOpenTickets.get(ticketId);
        String exitTime = LocalDateTime.now().toString();
        //Calculate payment based on diff of ticket entryTime and exitTime
        int vehicleSize = getVehicleType(ticket.vehicle.vehicleSize);
        mapOfOpenTickets.remove(ticketId);
        availableSpaces[vehicleSize]++;
        return 0.0;
    }

    public static Ticket entry(Vehicle vehicle) {
        String ticketId = UUID.randomUUID().toString();
        Ticket ticket = new Ticket(ticketId, vehicle, LocalDateTime.now().toString());
        int vehicleSize = getVehicleType(vehicle.vehicleSize);
        availableSpaces[vehicleSize]--;
        mapOfOpenTickets.put(ticketId, ticket);
        return ticket;
    }

    public static int getVehicleType(String vehicleSize) {
        if (vehicleSize.equals("SMALL")) {
            return 0;
        } else if (vehicleSize.equals("MEDIUM")) {
            return 1;
        } else {
            return 2;
        }
    }
}
