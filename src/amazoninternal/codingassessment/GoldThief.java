package amazoninternal.codingassessment;

import java.io.IOError;
import java.io.IOException;

/**
 * There are n houses in the street and each has some gold bars in it.
 * A thief is going to steal maximum bars for houses but
 * he can't steal in two adjacent houses. Find max profit
 * input = [5, 2, 7, 3, 1] - 5 + 7 + 1 = 13 or 2 + 3 = 5 , so 13 is the max profit
 */

public class GoldThief {
    public static void main(String args[]) throws IOException {
        int[] input = {5, 2, 7, 3, 1};
        System.out.println("Total profit : " + getTotalProfit(input));
    }

    private static int getTotalProfit(int[] input) {

        /*
        * Recursive approach
        private static int getTotalProfitRecursive(int[] input) {
            return rob(input, 0);
        }
         private static int rob(int[] input, int idx) {
            if (idx >= input.length)
                return 0;

            // Option 1: Rob current house → go to idx + 2
            int rob = input[idx] + rob(input, idx + 2);

            // Option 2: Skip current house → go to idx + 1
            int skip = rob(input, idx + 1);

            return Math.max(rob, skip);
        }
        * TC - O(2^n) — because every index branches into 2 choices.
        *
        * Memoization top-down - Memoization Converts Exponential Recursion → Linear DP
        private static int getTotalProfit(int[] input) {
            Integer[] memo = new Integer[input.length];
            return rob(input, 0, memo);
        }

        private static int rob(int[] input, int idx, Integer[] memo) {
            if (idx >= input.length)
                return 0;

            if (memo[idx] != null)
                return memo[idx];

            int rob = input[idx] + rob(input, idx + 2, memo);
            int skip = rob(input, idx + 1, memo);

            memo[idx] = Math.max(rob, skip);
            return memo[idx];
        }
        */
        int len = input.length;
        if (len == 1)
            return input[0];
        else if (len == 2)
            return Math.max(input[0], input[1]);
        else {
            int[] dp = new int[len];
            dp[0] = input[0];
            dp[1] = Math.max(input[0], input[1]);
            for (int idx = 2; idx < len; idx++) {
                dp[idx] = Math.max(dp[idx - 2] + input[idx], dp[idx - 1]);
            }
            return dp[len - 1];
        }
    }
}
