package uber;

import java.util.Arrays;

public class MinimumSum {
    public static void main(String[] args) {
        int[] arr = {3, 1, 0, 5, 1, 6, 5, -1, -100};
        int p = 1, q = 1, r = 1;
        int result = computeSum(arr, p, q, r);
        System.out.println("Minimum sum of selected elements: " + result); // Output: -96
    }

    public static int computeSum(int[] arr, int p, int q, int r) {
        Arrays.sort(arr); // Sort the array to easily pick smallest elements

        int n = arr.length;
        int minSum = Integer.MAX_VALUE;

        // Iterate through possible starting points for subarrays
        for (int i = 0; i <= n - p - 2*q - 3*r; i++) {
            int sum = 0;
            int index = i;

            // Select p subarrays of size 1
            for (int j = 0; j < p; j++) {
                sum += arr[index++];
            }

            // Select q subarrays of size 2
            for (int j = 0; j < q; j++) {
                sum += arr[index++] + arr[index++];
            }

            // Select r subarrays of size 3
            for (int j = 0; j < r; j++) {
                sum += arr[index++] + arr[index++] + arr[index++];
            }

            // Update the minimum sum found
            minSum = Math.min(minSum, sum);
        }

        return minSum;
    }
}
