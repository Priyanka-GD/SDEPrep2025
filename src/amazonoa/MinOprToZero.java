package amazonoa;

public class MinOprToZero {

    private static int getMinOpr(int[] nums) {

        int n = nums.length;

        int sum = 0;
        for(int idx = 0; idx < nums.length - 1; idx++){
            sum += Math.abs(nums[idx] - nums[idx + 1]);
        }
        return sum + Math.abs(nums[n - 1]);
    }

    public static void main(String[] args) {
        int[] nums = {3, 2, 0, 0, -1};
        System.out.println("Result : " + getMinOpr(nums));
    }
}
