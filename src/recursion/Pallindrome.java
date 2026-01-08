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
