package multithreading.concurrency.queue;

public class TestNonBlockingQueue {
    public static void main(String[] args) {
        NonBlockingQueue queue = new NonBlockingQueue(3);
        System.out.println(queue.add(1));  // true
        System.out.println(queue.add(2));  // true
        System.out.println(queue.add(3));  // true
        System.out.println(queue.add(4));  // false (queue is full)

        // Consumer trying to remove items
        System.out.println(queue.remove());  // 1
        System.out.println(queue.remove());  // 2
        System.out.println(queue.remove());  // 3
        System.out.println(queue.remove());  // null (queue is empty)
    }
}
