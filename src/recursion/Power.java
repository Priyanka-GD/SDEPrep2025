package recursion;

public class Power {
    public static void main(String[] args) {
        System.out.println(getPower(5, 4));
    }

    private static int getPower(int num, int power) {
        if(power == 0)
            return 1;
        return getPower(num, power - 1) * num;
    }
}
/*
* Each recursive call reduces power by 1, and each call does O(1) work (a multiply + return).
* TC : O(power)
 * Because it’s recursion, you build a call stack of depth power.
* SC : O(power)
* */
