package amazoninternal.codingassessment;

import java.util.*;

public class PrefixSumMap {
    public static void main(String[] args) {
        int a[] = {3, -5, 2, -1, 3, 2, -8, 3};
        System.out.println("Result is : " + Arrays.toString(getMaxSum(a)));
    }

    public static int[] getMaxSum(int[] a) {
        int[] result = {-1, -1};
        if (a == null || a.length == 0) return result;

        int len = a.length;
        int maxSum = Integer.MIN_VALUE;
        int runningPrefixSum = 0;

        // Maps element value -> [minPrefixSumBeforeIndex, bestStartIndex]
        Map<Integer, int[]> minPrefixMap = new HashMap<>();

        for (int j = 0; j < len; j++) {
            runningPrefixSum += a[j];
            int val = a[j];

            // Prefix sum right BEFORE index j starts (i.e., prefixSum[j-1])
            int prevPrefixSum = runningPrefixSum - val;

            if (minPrefixMap.containsKey(val)) {
                int[] prevInfo = minPrefixMap.get(val);
                int minPrevPrefix = prevInfo[0];
                int startIndex = prevInfo[1];

                // Subarray sum = prefixSum[j] - prefixSum[i-1]
                int currentSubarraySum = runningPrefixSum - minPrevPrefix;

                if (currentSubarraySum > maxSum) {
                    maxSum = currentSubarraySum;
                    result[0] = startIndex;
                    result[1] = j;
                }

                // Keep the entry with the MINIMUM prefix sum for future occurrences of 'val'
                if (prevPrefixSum < minPrevPrefix) {
                    minPrefixMap.put(val, new int[]{prevPrefixSum, j});
                }
            } else {
                // First time seeing this element value
                minPrefixMap.put(val, new int[]{prevPrefixSum, j});
            }
        }

        return result;
    }
}