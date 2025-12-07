package multithreading.youtubelearn;

public class Stack {
    private int[] array;
    private int stackTop;
    private Object lock;

    public Stack(int capacity) {
        array = new int[capacity];
        stackTop = -1;
        lock = new Object();
    }

    public boolean isEmpty() {
        return stackTop < 0;
    }

    public boolean isFull() {
        return stackTop >= array.length;
    }

    public synchronized boolean push(int element) {
        //Every object can be used as a synchronized , not primitive type
        //On writing synchronized in method signature, compiler is enclosing the code in synchronized(this)
        //synchronized (lock) {
        if (isFull())
            return false;
        ++stackTop;
        try {
            Thread.sleep(1000);
            //This thread will sleep for 1s and thread execution is blocked
        } catch (Exception e) {

        }
        array[stackTop] = element;
        return true;
        //}
    }

    public synchronized int pop() {
        //synchronized (lock) {
        if (isEmpty())
            return Integer.MIN_VALUE;
        int obj = array[stackTop];
        array[stackTop] = Integer.MIN_VALUE;
        try {
            Thread.sleep(1000);
        } catch (Exception e) {

        }
        stackTop--;
        return obj;
        //}
    }

}
