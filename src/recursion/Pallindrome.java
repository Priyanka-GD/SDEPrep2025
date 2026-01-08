package recursion;

public class Pallindrome {
    public static void main(String[] args) {
        System.out.println(isPalindrome(141));
    }

    static boolean isPalindrome(int num) {
        return num == reverse(num, 0);
    }

    static int reverse(int num, int rev) {
        if (num == 0)
            return rev;
        return reverse(num / 10, rev * 10 + num % 10);
    }
}

/*
CALLS (going down)

1) reverse(141, 0)
2) reverse(14,  0*10 + 141%10) = reverse(14, 1)
3) reverse(1,   1*10 + 14%10)  = reverse(1, 14)
4) reverse(0,   14*10 + 1%10)  = reverse(0, 141)

RETURNS (unwinding)

5) reverse(0, 141) returns 141
6) reverse(1, 14)  returns 141
7) reverse(14, 1)  returns 141
8) reverse(141, 0) returns 141

*/

/*
Time Complexity (TC)

Let d = number of digits in num

Each recursive call:

removes one digit (num / 10)

does O(1) work (mod, multiply, add)

Total calls = d

✅ TC = O(d)
(or O(log₁₀ n) if you express it in terms of the number)

💾 Space Complexity (SC)

Recursion depth = d

No extra data structures

✅ SC = O(d) (due to call stack)
* */
