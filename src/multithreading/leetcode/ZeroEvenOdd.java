package multithreading.leetcode;

import java.util.function.IntConsumer;

//https://leetcode.com/problems/print-zero-even-odd/
class ZeroEvenOdd {
    private int n;
    private int printInt = 1;  // Initialize printInt to 1 to start from 1
    private boolean toPrintZero = true;

    public ZeroEvenOdd(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        synchronized (this) {
            for (int i = 0; i < n; i++) {
                while (!toPrintZero) {
                    wait();
                }
                printNumber.accept(0);
                toPrintZero = false;
                notifyAll();
            }
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        synchronized (this) {
            for (int i = 2; i <= n; i += 2) {
                while (toPrintZero || printInt % 2 != 0) {
                    wait();
                }
                printNumber.accept(printInt);
                printInt++;
                toPrintZero = true;
                notifyAll();
            }
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        synchronized (this) {
            for (int i = 1; i <= n; i += 2) {
                while (toPrintZero || printInt % 2 == 0) {
                    wait();
                }
                printNumber.accept(printInt);
                printInt++;
                toPrintZero = true;
                notifyAll();
            }
        }
    }
}
