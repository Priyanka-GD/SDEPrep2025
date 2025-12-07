package amazonoa;

import java.util.*;

public class MinCostArrayDistinct {
    public static void main(String[] args) {
        int[] size = {2, 3, 3, 2};
        int[] cost = {2, 4, 5, 1};
        System.out.println("Result : " + minCostToMakeArrayDistinct(size, cost));

    }

    public static int minCostToMakeArrayDistinct(int[] size, int[] cost) {
        int n = size.length;

        // Min-heap based on size, and if equal, higher cost first
        PriorityQueue<int[]> heap = new PriorityQueue<>(
                (a, b) -> {
                    if (a[0] != b[0]) return a[0] - b[0];        // smaller size first
                    return b[1] - a[1];                          // larger cost first
                }
        );

        for (int i = 0; i < n; i++) {
            heap.offer(new int[]{size[i], cost[i]});
        }

        int result = 0;
        Integer prev = null;

        while (!heap.isEmpty()) {
            int[] curr = heap.poll();
            int currSize = curr[0];
            int currCost = curr[1];

            if (prev == null || currSize != prev) {
                prev = currSize;
            } else {
                result += currCost;
                System.out.println("CurrentSize : " + (currSize + 1) + " Current Cost : " + currCost);
                heap.offer(new int[]{currSize + 1, currCost});
            }
        }

        return result;
    }

}
/*
* I noticed that duplicates require resolving, and the cost to resolve depends not just on how far we increment,
* but also on how expensive each increment is. So I used a greedy strategy: sort the input by value and push pairs
* into a min-heap to efficiently resolve conflicts. Whenever I see a duplicate, I increment it and calculate the cost
* using its own cost-per-increment. The heap ensures I always process the smallest available value,
* and I maintain uniqueness by reinserting incremented elements as needed
*
* Time complexity O(n log n)
* Space complexity O(n)
*  */
