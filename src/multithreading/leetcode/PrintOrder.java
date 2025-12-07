package multithreading.leetcode;

//https://leetcode.com/problems/print-in-order/description/
class PrintOrder {
    Integer integer;

    public PrintOrder() {
        integer = 0;
    }

    public void first(Runnable printFirst) throws InterruptedException {
        synchronized (this) {
            while (integer != 0) {
                wait();
            }
            printFirst.run();
            integer = 1;
            notifyAll();
        }
        // printFirst.run() outputs "first". Do not change or remove this line.

    }

    public void second(Runnable printSecond) throws InterruptedException {
        synchronized (this) {
            while (integer != 1) {
                wait();
            }
            printSecond.run();
            integer = 2;
            notifyAll();
        }
        // printSecond.run() outputs "second". Do not change or remove this line
    }

    public void third(Runnable printThird) throws InterruptedException {
        synchronized (this) {
            while (integer != 2) {
                wait();
            }
            printThird.run();
            integer = 3;
            notifyAll();
        }
        // printThird.run() outputs "third". Do not change or remove this line.

    }
}