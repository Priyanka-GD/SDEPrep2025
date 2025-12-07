package googleOA;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LongestCommonSubsequenceSorted {
    public static List<Integer> longestCommonSubsequence(int[][] arrays) {

        List<Integer> longestCommonSubseq = new ArrayList<>();
        for (int num : arrays[0]) {
            longestCommonSubseq.add(num);
        }
        for (int i = 1; i < arrays.length; i++) {
            if (longestCommonSubseq.isEmpty()) {
                return longestCommonSubseq;
            }
            longestCommonSubseq = longestSeq(longestCommonSubseq, arrays[i]);
        }
        return longestCommonSubseq;
    }

    private static List<Integer> longestSeq(List<Integer> nums1, int[] nums2) {
        List<Integer> longestCommonSeq = new ArrayList<>();
        int first = 0;
        int second = 0;
        // {2, 2, 3, 6, 8}
        // {1, 2, 2, 3, 5, 6, 7, 10}
        while (first < nums1.size() && second < nums2.length) {
            System.out.println("First " + nums1.get(first) +" Second " + nums2[second]);
            if (nums1.get(first) < nums2[second]) {
                first++;
            } else if (nums1.get(first) > nums2[second]) {
                second++;
            } else {
                longestCommonSeq.add(nums1.get(first));
                first++;
                second++;
            }
            System.out.println(longestCommonSeq);
        }
        return longestCommonSeq;
    }

    public static void main(String args[]) throws IOException {
        int arr[][] = {{2, 2, 3, 6, 8}, {1, 2, 2, 3, 5, 6, 7, 10}, {2, 2, 2, 3, 4, 6, 9}};
        List<Integer> result = longestCommonSubsequence(arr);
        System.out.println("Result is " + result);
    }
}
