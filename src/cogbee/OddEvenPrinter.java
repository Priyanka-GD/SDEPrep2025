package cogbee;

public class OddEvenPrinter {
    /*
    This is an object that both threads synchronize on. It acts as the shared resource used
    to control the threads' execution, ensuring that both threads don’t print simultaneously.
    The wait() and notify() methods will operate on this object.
    */
    private static final Object lock = new Object();
    private static boolean isOddTurn = true; // Indicates whose turn it is to print

    public static void main(String[] args) {
        OddThread oddThread = new OddThread();
        oddThread.setName("Thread-1");
        EvenThread evenThread = new EvenThread();
        evenThread.setName("Thread-2");

        oddThread.start();
        evenThread.start();
    }

    static class OddThread extends Thread {
        @Override
        public void run() {
            for (int i = 1; i <= 10; i += 2) {
                synchronized (lock) {
                    while (!isOddTurn) {
                        try {
                            lock.wait(); // Wait if it's not the odd thread's turn
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("Thread " + Thread.currentThread().getName() + ": " + i);
                    isOddTurn = false; // Now it's the even thread's turn
                    lock.notify(); // Notify the even thread
                }
            }
        }
    }

    static class EvenThread extends Thread {
        @Override
        public void run() {
            for (int i = 2; i <= 10; i += 2) {
                synchronized (lock) {
                    while (isOddTurn) {
                        try {
                            lock.wait(); // Wait if it's not the even thread's turn
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("Thread " + Thread.currentThread().getName() + ": " + i);
                    isOddTurn = true; // Now it's the odd thread's turn
                    lock.notify(); // Notify the odd thread
                }
            }
        }
    }
}


