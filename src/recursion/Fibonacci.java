package recursion;

public class Fibonacci {
    public static void main(String[] args) {
        System.out.println(fibSumFirstN(5)); // 7
    }

    static int fib(int n) {
        if (n <= 1) return n;
        return fib(n - 1) + fib(n - 2);
    }

    // sums fib(0) + fib(1) + ... + fib(n-1)
    static int fibSumFirstN(int n) {
        if (n <= 0) return 0;
        return fib(n - 1) + fibSumFirstN(n - 1);
    }
}
