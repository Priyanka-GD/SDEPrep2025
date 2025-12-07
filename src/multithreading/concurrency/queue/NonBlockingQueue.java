package multithreading.concurrency.queue;

import java.util.LinkedList;
import java.util.Queue;

public class NonBlockingQueue {
    private final Queue<Integer> queue;
    private final int capacity;

    public NonBlockingQueue(int capacity) {
        this.queue = new LinkedList<>();
        this.capacity = capacity;
    }

    // Non-blocking add - returns false if queue is full
    public synchronized boolean add(int item) {
        if (queue.size() == capacity) {
            System.out.println("Queue is full..Producer can't add item " + item);  // Immediate feedback
            return false;  // No blocking, return immediately
        }
        queue.add(item);
        System.out.println("Item added: " + item);
        notifyAll();  // In case a consumer is waiting (optional in non-blocking cases)
        return true;
    }

    // Non-blocking remove - returns null if queue is empty
    public synchronized Integer remove() {
        if (queue.isEmpty()) {
            System.out.println("Queue is empty..Consumer can't remove item");
            return null;  // No blocking, return immediately
        }
        Integer item = queue.poll();
        System.out.println("Item removed: " + item);
        notifyAll();  // In case a producer is waiting (optional in non-blocking cases)
        return item;
    }

    // Just a helper method to check current size
    public synchronized int size() {
        return queue.size();
    }
}
