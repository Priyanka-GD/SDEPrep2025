package discord.August22;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

//219. Contains Duplicate II
public class ContainsDuplicateII {
    public static void main(String args[]) throws IOException {
        //int nums[] = {1, 2, 3, 1};
        //int k = 3;
        int nums[] = {1, 2, 3, 1, 2, 3};
        int k = 2;
        boolean containsDuplicate = getDuplicateCountII(nums, k);
        System.out.println("Contains Duplicate  : " + containsDuplicate);
    }

    private static boolean getDuplicateCountII(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int idx = 0; idx < nums.length; idx++) {
            if (map.containsKey(nums[idx])) {
                int absDiffInIdx = Math.abs(map.get(nums[idx]) - idx);
                if (absDiffInIdx <= k)
                    return true;
            }
            map.put(nums[idx], idx);
        }
        return false;
    }
}
