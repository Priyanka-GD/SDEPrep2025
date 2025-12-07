package multithreading.leetcode;

import java.util.LinkedList;
import java.util.Queue;

class BoundedBlockingQueue {
    int capacity;
    Queue<Integer> queue;

    public BoundedBlockingQueue(int capacity) {
        this.queue = new LinkedList<>();
        this.capacity = capacity;
    }

    public synchronized void enqueue(int element) throws InterruptedException {
        while (queue.size() == capacity)
            wait();
        queue.add(element);
        notifyAll();

    }

    public synchronized int dequeue() throws InterruptedException {
        while (queue.isEmpty())
            wait();
        int ele = queue.remove();
        notifyAll();
        return ele;
    }

    public int size() {
        return queue.size();
    }
}