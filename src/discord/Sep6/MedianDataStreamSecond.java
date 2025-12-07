package discord.Sep6;

import java.util.Collections;
import java.util.PriorityQueue;

public class MedianDataStreamSecond {
    PriorityQueue<Integer> firstQueue;
    PriorityQueue<Integer> secondQueue;
    boolean even;

    public MedianDataStreamSecond() {
        firstQueue = new PriorityQueue<>(Collections.reverseOrder());
        secondQueue = new PriorityQueue<>();
        even = true;
    }

    public void addNum(int num) {
        if (even) {
            secondQueue.add(num);
            firstQueue.add(secondQueue.poll());
        } else {
            firstQueue.add(num);
            secondQueue.add(firstQueue.poll());
        }
        even = !even;
    }

    public double findMedian() {
        if (!even)
            return firstQueue.peek();
        else
            return (secondQueue.peek() + firstQueue.peek()) / 2.0;
    }

}
