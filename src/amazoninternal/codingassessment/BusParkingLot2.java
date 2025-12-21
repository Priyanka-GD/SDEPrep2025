package amazoninternal.codingassessment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/*
Given an array of buses arrival in a day.
Job is to assign bus inside the parking lot only if the parking lot has space when the bus is arriving
*/

public class BusParkingLot2 {
    public static void main(String[] args) {
        Bus bus1 = new Bus(new int[]{1, 3}, 1); // true - 1
        Bus bus2 = new Bus(new int[]{3, 5}, 1); // true - 1
        Bus bus3 = new Bus(new int[]{4, 6}, 1); // false

        Bus bus4 = new Bus(new int[]{1, 3}, 2); // true - 2
        Bus bus5 = new Bus(new int[]{3, 5}, 2); // true - 2
        Bus bus6 = new Bus(new int[]{7, 11}, 2); // true - 1/2

        Bus bus7 = new Bus(new int[]{1, 9}, 3); // true - 3
        Bus bus8 = new Bus(new int[]{3, 5}, 3); // false
        Bus bus9 = new Bus(new int[]{10, 14}, 3); // true - 2/3

        Bus[] buses = new Bus[]{bus1, bus9, bus2, bus6, bus7, bus4, bus3, bus8, bus5};

        List<Bus> listOfBuses = getBusesWhoCanEnterParkingLot(buses, 5);
        for (Bus bus : listOfBuses) {
            bus.printBusTiming();
        }
    }

    private static List<Bus> getBusesWhoCanEnterParkingLot(Bus[] buses, int spaces) {
        Arrays.sort(buses, (bus1, bus2) -> bus1.time[0] - bus2.time[0]);
        PriorityQueue<Bus> minHeap = new PriorityQueue<>((bus1, bus2) -> bus1.time[1] - bus2.time[1]);
        List<Bus> previousBuses = new ArrayList<>();
        for (Bus bus : buses) {
            int arrive = bus.time[0];
            if (!minHeap.isEmpty() && minHeap.peek().time[1] < arrive) {
                minHeap.poll();
            }
            if (minHeap.size() < spaces) {
                minHeap.offer(bus);
                bus.allowBus();
                previousBuses.add(bus);
            }
        }
        return previousBuses;
    }
}

class Bus {
    int[] time;
    int size;
    boolean canEnter;

    public Bus(int[] time, int size) {
        this.time = time;
        this.size = size;
    }

    public void allowBus() {
        this.canEnter = true;
    }

    public void printBusTiming() {
        System.out.println("Bus details : " + Arrays.toString(time));
    }
}
