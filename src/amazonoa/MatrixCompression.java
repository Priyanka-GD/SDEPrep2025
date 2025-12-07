package amazonoa;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class MatrixCompression {
    public static void main(String[] args) {
        // Example input
        int n = 3;
        int[][] data = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        int[] factor = {1, 2, 1};
        int x = 2;
        long result = findMaxValue(data, factor, n, x);
        System.out.println(result);
    }

    private static long findMaxValue(int[][] data, int[] factor, int n, int x) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        int totalElements = 0;
        for (int row = 0; row < n; row++) {
            Arrays.sort(data[row]);
            int limit = Math.min(factor[row], n);
            totalElements += limit;

            for (int idx = n - 1; idx >= n - limit; idx--) {
                maxHeap.offer(data[row][idx]);
            }
        }
        if (totalElements < x) return -1;
        long maxSum = 0;
        for (int i = 0; i < x; i++) {
            maxSum += maxHeap.poll();
        }

        return maxSum;
    }
}
