package multithreading.leetcode;
//1115. Print FooBar Alternately
class FooBar {
    private int n;
    private static boolean toPrintFoo = true;
    private static final Object lock = new Object();

    public FooBar(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            synchronized (lock) {
                while (!toPrintFoo) {
                    lock.wait();
                }
                // printFoo.run() outputs "foo". Do not change or remove this line.
                printFoo.run();
                toPrintFoo = !toPrintFoo;
                lock.notifyAll();
            }
        }

    }

    public void bar(Runnable printBar) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            synchronized (lock) {
                while (toPrintFoo) {
                    lock.wait();
                }
                // printBar.run() outputs "foo". Do not change or remove this line.
                printBar.run();
                toPrintFoo = !toPrintFoo;
                lock.notifyAll();
            }
        }
    }

}