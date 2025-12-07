package multithreading.leetcode;

import java.io.IOException;

public class OddEvenPrinterVolatile {

    private static volatile boolean isOddTurn = true;

    public static void main(String args[]) throws IOException {
        OddPrinter oddPrinter = new OddPrinter();
        Thread thread1 = new Thread(oddPrinter, "OddThread");

        EvenPrinter evenPrinter = new EvenPrinter();
        Thread thread2 = new Thread(evenPrinter, "EvenThread");

        thread1.start();
        thread2.start();
    }

    static class OddPrinter implements Runnable {
        @Override
        public void run() {
            for (int i = 1; i <= 10; i += 2) {
                while (!isOddTurn) {
                    // Busy-waiting (CPU-intensive, not recommended)
                }
                System.out.println(Thread.currentThread().getName() + " " + i);
                isOddTurn = false;
            }
        }
    }

    static class EvenPrinter implements Runnable {
        @Override
        public void run() {
            for (int i = 2; i <= 10; i += 2) {
                while (isOddTurn) {
                    // Busy-waiting (CPU-intensive, not recommended)
                }
                System.out.println(Thread.currentThread().getName() + " " + i);
                isOddTurn = true;
            }
        }
    }
}
