package multithreading.leetcode;

import java.util.function.IntConsumer;

//https://leetcode.com/problems/fizz-buzz-multithreaded/description/
class FizzBuzz {
    private int n;
    private int currNum;

    public FizzBuzz(int n) {
        this.n = n;
        currNum = 1;
    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        synchronized (this) {
            while (currNum <= n) {
                while (currNum <= n && (currNum % 3 != 0 || currNum % 5 == 0)) {
                    wait();
                }
                if (currNum > n)
                    break;
                printFizz.run();
                currNum++;
                notifyAll();
            }
        }
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        synchronized (this) {
            while (currNum <= n) {
                while (currNum <= n && (currNum % 3 == 0 || currNum % 5 != 0)) {
                    wait();
                }
                if (currNum > n)
                    break;
                printBuzz.run();
                currNum++;
                notifyAll();
            }
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        synchronized (this) {
            while (currNum <= n) {
                while (currNum <= n && (currNum % 3 != 0 || currNum % 5 != 0)) {
                    wait();
                }
                if (currNum > n)
                    break;
                printFizzBuzz.run();
                currNum++;
                notifyAll();
            }
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        synchronized (this) {
            while (currNum <= n) {
                while (currNum <= n && (currNum % 3 == 0 || currNum % 5 == 0)) {
                    wait();
                }
                if (currNum > n)
                    break;
                printNumber.accept(currNum);
                currNum++;
                notifyAll();
            }
        }
    }
}