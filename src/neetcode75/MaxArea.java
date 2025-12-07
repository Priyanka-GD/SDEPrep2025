package neetcode75;
//https://leetcode.com/problems/container-with-most-water/description/
public class MaxArea {
    public int maxArea(int[] heights) {
        int maxArea = Integer.MIN_VALUE;

        int right = heights.length - 1, left = 0;
        while (left < right) {
            int height = Math.min(heights[right], heights[left]);
            int width = right - left;
            maxArea = Math.max(maxArea, width * height);

            if (heights[left] <= heights[right])
                left++;
            else
                right--;
        }
        return maxArea;
    }
}
