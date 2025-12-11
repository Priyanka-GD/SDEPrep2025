package amazoninternal.codingassessment;

import java.util.*;

public class PairSum {
    public static void main(String args[]) {
        int input[] = {1, 3, 6, 2, 3, 4};
        List<int[]> result = getPairSumPairs(input, 5);
        System.out.println("Pair sum up to target: ");
        for (int[] arr : result) {
            System.out.println(Arrays.toString(arr));
        }
    }

    /*private static List<int[]> getPairSumPairs(int[] input, int target) {
        Set<Integer> seen = new HashSet<>();
        List<int[]> result = new ArrayList<>();
        for (int i : input) {
            int diff = target - i;
            if (seen.contains(diff)) {
                result.add(new int[]{i, diff});
            }
            seen.add(i);
        }
        return result;
    }*/
    //Updated logic (handles negatives + no duplicate pairs)
    private static List<int[]> getPairSumPairs(int[] input, int target) {
        Set<Integer> seen = new HashSet<>();
        // To avoid duplicate unordered pairs like (1,4) and (4,1)
        Set<String> pairSeen = new HashSet<>();

        List<int[]> result = new ArrayList<>();

        for (int num : input) {
            int diff = target - num;

            if (seen.contains(diff)) {
                // Canonical representation of pair (smaller, larger)
                int a = Math.min(num, diff);
                int b = Math.max(num, diff);
                String key = a + "#" + b;

                if (!pairSeen.contains(key)) {
                    // You can store (a,b) or (num,diff), depending on your preference
                    result.add(new int[]{a, b});
                    pairSeen.add(key);
                }
            }
            // Always add current number after checking
            seen.add(num);
        }
        return result;
    }

}
