package tiktok;
public class GoodSubarray {

    public static int maxGoodSubarrayLength(int[] arr, int L) {
        int n = arr.length;
        int maxLength = 0;

        int[] dp = new int[n]; // DP array to store max length of good subarray ending at each index

        for (int i = 0; i < n; i++) {
            double threshold = (double) L / (dp[i] + 1);

            if (arr[i] > threshold) {
                dp[i] = (i > 0 ? dp[i - 1] : 0) + 1; // Extend the previous good subarray
            } else {
                dp[i] = 0; // Reset the length if the condition is not met
            }

            maxLength = Math.max(maxLength, dp[i]);
        }

        return maxLength;
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 4, 3, 1};
        int L = 6; // Example value for L
        System.out.println("Maximum length of good subarray: " + maxGoodSubarrayLength(arr, L));
    }
}


/*public class GoodSubarray {

    public static int maxGoodSubarrayLength(int[] arr, int L) {
        int n = arr.length;
        int maxLength = -1;

        // Sliding window approach
        /*for (int length = 1; length <= n; length++) {
            double threshold = (double) L / length;
            int left = 0;

            // Check each subarray of the current length
            for (int right = 0; right < n; right++) {
                // Ensure window size is exactly the desired length
                if (right - left + 1 > length) {
                    left++;
                }

                // If window size is exactly the desired length
                if (right - left + 1 == length) {
                    // Check if all elements in the window are greater than threshold
                    boolean valid = true;
                    for (int i = left; i <= right; i++) {
                        if (arr[i] <= threshold) {
                            valid = false;
                            break;
                        }
                    }
                    if (valid) {
                        maxLength = Math.max(maxLength, length);
                    }
                }
            }
        }*/


