package multithreading.leetcode;
//https://leetcode.com/problems/building-h2o/description/
class H2O {
    int hydrogenCount;

    public H2O() {
        hydrogenCount = 0;
    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        synchronized (this) {
            while (hydrogenCount == 2)
                wait();
            releaseHydrogen.run();
            hydrogenCount++;
            notifyAll();
        }
        // releaseHydrogen.run() outputs "H". Do not change or remove this line.

    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        synchronized (this) {
            while (hydrogenCount < 2)
                wait();
            releaseOxygen.run();
            hydrogenCount = 0;
            notifyAll();
        }
        // releaseOxygen.run() outputs "O". Do not change or remove this line.

    }
}