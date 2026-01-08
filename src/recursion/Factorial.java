package recursion;

public class Factorial {
    public static void main(String[] args) {
        System.out.println(fact(5));
    }

    private static int fact(int num) {
        if(num <= 1)
            return 1;
        return fact(num - 1) * num;
    }
}
/*
// fact(4) * 5 = 120
    ^
    |
// fact(3) * 4 = 24
    ^
    |
//fact(2) * 3 = 6
    ^
    |
//fact(1) * 2 = 2
    ^
    |
//fact(1) = 1

TC - O(n)
Each call does constant work (* num)

SC - O(n)
| fact(5) |
| fact(4) |
| fact(3) |
| fact(2) |
| fact(1) |
Maximum stack depth = n, memory grows linear
*/
