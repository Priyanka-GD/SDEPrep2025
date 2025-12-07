package neetcode75;
//https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/description/
public class RotatedSortedMin {
    public int findMin(int[] nums) {
        if (nums.length == 1)
            return nums[0];
        int left = 0, right = nums.length - 1;
        if (nums[left] < nums[right])
            return nums[left];
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[mid + 1])
                return nums[mid + 1];
            else if (nums[mid] < nums[mid - 1])
                return nums[mid];
            if (nums[mid] > nums[0])
                left = mid + 1;
            else
                right = mid - 1;
        }
        return -1;
    }
}
