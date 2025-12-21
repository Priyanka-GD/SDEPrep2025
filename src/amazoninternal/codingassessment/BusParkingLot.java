package amazoninternal.codingassessment;
import java.util.Arrays;
import java.util.PriorityQueue;

public class BusParkingLot {
    public static void main(String[] args) {
        int[][] busTimings = new int[][]{{1, 3, 1}, {3, 5, 1}, {5, 7, 2}};
        System.out.println(Arrays.toString(canParkAll(busTimings)));
    }

    private static int[] canParkAll(int[][] busTimings) {
        Arrays.sort(busTimings, (a, b) -> Integer.compare(a[0], b[0]));

        // min-heap of end times of buses currently parked
        PriorityQueue<Integer> minHeapS = new PriorityQueue<>();
        PriorityQueue<Integer> minHeapM = new PriorityQueue<>();
        PriorityQueue<Integer> minHeapL = new PriorityQueue<>();

        for (int[] timing : busTimings) {
            int size = timing[2];
            int start = timing[0];
            int end = timing[1];
            switch (size) {
                case 1:
                    constructHeapWithParkingSpace(minHeapS, start, end);
                    break;
                case 2:
                    constructHeapWithParkingSpace(minHeapM, start, end);
                    break;
                case 3:
                    constructHeapWithParkingSpace(minHeapL, start, end);
                    break;
            }
        }
        return new int[]{minHeapS.size(), minHeapM.size(), minHeapL.size()};
    }

    private static void constructHeapWithParkingSpace(PriorityQueue<Integer> minHeap, int start, int end) {
        while (!minHeap.isEmpty() && minHeap.peek() <= start) {
            minHeap.poll();
        }
        // park this bus
        minHeap.offer(end);
    }

}

