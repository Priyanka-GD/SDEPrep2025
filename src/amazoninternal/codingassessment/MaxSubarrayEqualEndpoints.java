package amazoninternal.codingassessment;

import java.util.HashMap;
import java.util.Map;

public class MaxSubarrayEqualEndpoints {

    // Helper class to store the minimum prefix sum and index
    private static class PrefixInfo {
        long prefixSum;
        int index;

        PrefixInfo(long prefixSum, int index) {
            this.prefixSum = prefixSum;
            this.index = index;
        }
    }

    public static int[] maxSubarrayEqualEndpoints(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[]{-1, -1};
        }

        long currPrefixSum = 0;
        long maxSum = Long.MIN_VALUE;
        int bestI = 0;
        int bestJ = 0;

        // Map stores: value -> PrefixInfo(minPrefixSumBeforeIndex, index)
        Map<Integer, PrefixInfo> minPrefixMap = new HashMap<>();

        for (int j = 0; j < nums.length; j++) {
            int val = nums[j];
            long prefixBeforeJ = currPrefixSum;
            currPrefixSum += val;

            if (minPrefixMap.containsKey(val)) {
                PrefixInfo prevInfo = minPrefixMap.get(val);
                long currentSum = currPrefixSum - prevInfo.prefixSum;

                if (currentSum > maxSum) {
                    maxSum = currentSum;
                    bestI = prevInfo.index;
                    bestJ = j;
                }

                // Update the minimum prefix sum for this value if current is smaller
                if (prefixBeforeJ < prevInfo.prefixSum) {
                    minPrefixMap.put(val, new PrefixInfo(prefixBeforeJ, j));
                }
            } else {
                // First time seeing this value; handle single-element subarray case (i = j)
                if (val > maxSum) {
                    maxSum = val;
                    bestI = j;
                    bestJ = j;
                }
                minPrefixMap.put(val, new PrefixInfo(prefixBeforeJ, j));
            }
        }

        System.out.println("Max Sum: " + maxSum);
        return new int[]{bestI, bestJ};
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 2, 5};
        int[] result = maxSubarrayEqualEndpoints(nums);
        System.out.println("i = " + result[0] + ", j = " + result[1]);
        // Output: Max Sum: 7, i = 1, j = 3
    }
}
