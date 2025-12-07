package multithreading.learn;

public class OrderedNumberPrinterRunnable {
    private static final int TOTAL_NUMBERS = 30;
    private static final int TOTAL_THREADS = 3;  // Number of threads
    private static int number = 1; // Shared counter
    private static int turn = 0; // To track whose turn it is
    private static final Object lock = new Object(); // Lock object for synchronization

    public static void main(String[] args) {
        // Create and start 3 threads using Runnable
        for (int i = 0; i < TOTAL_THREADS; i++) {
            new Thread(new NumberPrinter(i)).start();
        }
    }

    // Runnable class for printing numbers in sequence
    static class NumberPrinter implements Runnable {
        private final int threadId;

        public NumberPrinter(int threadId) {
            this.threadId = threadId;
        }

        @Override
        public void run() {
            while (true) {
                synchronized (lock) {
                    // Wait until it's this thread's turn
                    while (turn != threadId) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }

                    // Stop execution if all numbers are printed
                    if (number > TOTAL_NUMBERS) {
                        lock.notifyAll(); // Notify all threads to exit
                        return;
                    }

                    // Print the current number
                    System.out.println("Thread-" + (threadId + 1) + " prints: " + number++);

                    // Update turn to the next thread
                    turn = (turn + 1) % TOTAL_THREADS;

                    // Notify all threads to check whose turn it is
                    lock.notifyAll();
                }
            }
        }
    }
}
