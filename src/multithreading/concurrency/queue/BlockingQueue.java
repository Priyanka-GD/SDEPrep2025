package multithreading.concurrency.queue;

import java.util.LinkedList;
import java.util.Queue;

/*
 * When you synchronize at the method level, you are synchronizing on the instance of the BlockingQueue itself (i.e., this).
 *  When you synchronize on queue, you are only synchronizing on the queue object.
 * Since queue is a private field, no external code can lock on it, so it’s generally safe. But if you want to simplify
 * and synchronize on the whole BlockingQueue instance itself, you can do this by using synchronized on the methods.
 */
public class BlockingQueue {
    private final Queue<Integer> queue;
    private final int capacity;

    public BlockingQueue(int capacity) {
        this.queue = new LinkedList<>();
        this.capacity = capacity;
    }

    public boolean add(int item) {
        synchronized (queue) {
            while (queue.size() == capacity) {
                try {
                    System.out.println("Queue is full..Producers need to wait");// blocking
                    queue.wait();
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            }
            queue.add(item);
            queue.notifyAll();// Notify threads in wait state who are waiting for queue to have elements
            return true;
        }
    }

    public int remove() {
        synchronized (queue) {
            while (queue.isEmpty()) {
                try {
                    System.out.println("No elements in the queue..Consumers need to wait");// blocking
                    queue.wait();
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            }
            //do nothing
            int ele = queue.poll();
            queue.notifyAll();// So when an element is popped, it notifies thread to add items to queue
            return ele;
        }
    }
}
