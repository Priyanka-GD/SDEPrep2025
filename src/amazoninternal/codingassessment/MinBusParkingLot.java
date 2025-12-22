package amazoninternal.codingassessment;

import java.util.Arrays;
import java.util.PriorityQueue;

public class MinBusParkingLot {
    public static void main(String[] args) {
        int[][] busTimings = {{9, 11}, {10, 13}, {11, 12}};
        System.out.println("Minimum number of parking lots : " + getMinParkingSpots(busTimings));
    }

    public static int getMinParkingSpots(int[][] busTimings) {
        Arrays.sort(busTimings,
                (busTiming1, busTiming2) -> Integer.compare(busTiming1[0], busTiming2[0])
        );
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int[] busTiming : busTimings) {
            if (!minHeap.isEmpty() && minHeap.peek() <= busTiming[1]) {
                minHeap.poll();
            }
            minHeap.add(busTiming[1]);
        }
        return minHeap.size();
    }
}
