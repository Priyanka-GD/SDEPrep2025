package amazoninternal.codingassessment;

import java.util.*;

public class MinBusParkingLotBySize {
    public static void main(String[] args) {
        int[][] busTimings = {
                {1,  9, 11, 0}, // S
                {2, 11, 12, 0}, // S
                {3, 10, 13, 0}  // S
        };
        System.out.println("Min Bus ParkingLot BySize : " + Arrays.toString(getMinParkingSpotsBySize(busTimings)));
    }
    static class Release {
        int outTime;
        int spotSize; // 0=S,1=M,2=L

        Release(int outTime, int spotSize) {
            this.outTime = outTime;
            this.spotSize = spotSize;
        }
    }

    public static int[] getMinParkingSpotsBySize(int[][] buses) {

        Arrays.sort(buses, (a, b) -> {
            if (a[1] != b[1]) return Integer.compare(a[1], b[1]);
            return Integer.compare(a[2], b[2]);
        });

        PriorityQueue<Release> pq =
                new PriorityQueue<>(Comparator.comparingInt(r -> r.outTime));

        int[] free = new int[3]; // free spots per size
        int[] total = new int[3]; // total spots per size

        for (int[] bus : buses) {
            int in = bus[1];
            int out = bus[2];
            int minSize = bus[3];

            // release finished spots
            while (!pq.isEmpty() && pq.peek().outTime <= in) {
                Release r = pq.poll();
                free[r.spotSize]++;
            }

            // find the smallest usable spot
            int assigned = -1;
            for (int s = minSize; s < 3; s++) {
                if (free[s] > 0) {
                    free[s]--;
                    assigned = s;
                    break;
                }
            }

            // if none found, create new spot
            if (assigned == -1) {
                assigned = minSize;
                total[assigned]++;
            }

            pq.add(new Release(out, assigned));
        }

        return total; // [S, M, L]
    }
}
