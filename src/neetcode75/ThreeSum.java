package neetcode75;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
//https://leetcode.com/problems/3sum/
class ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> results = new ArrayList<>();

        for (int idx = 0; idx < nums.length - 2; idx++) {
            if (idx == 0 || (idx > 0 && nums[idx - 1] != nums[idx])) {
                int high = nums.length - 1, low = idx + 1, sum = 0 - nums[idx];
                while (low < high) {
                    if (nums[low] + nums[high] == sum) {
                        results.add(Arrays.asList(nums[low], nums[high], nums[idx]));

                        while (low < high && nums[low] == nums[low + 1]) {
                            low++;
                        }
                        while (low < high && nums[high] == nums[high - 1]) {
                            high--;
                        }
                        low++;
                        high--;
                    } else if (nums[low] + nums[high] > sum)
                        high--;
                    else
                        low++;
                }
            }
        }
        return results;
    }
}
