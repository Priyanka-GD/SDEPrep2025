package amazoninternal.codingassessment;

import java.util.List;
import java.util.PriorityQueue;

/*
 * Given N sorted iterators, create a single one that provides their elements sorted
 * iterator A : 1, 3, 7
 * iterator B : 1, 2, 5, 7, 11 , 20
 * iterator C : 0, 4
 * output iterator : 0, 1, 1, 2, 3, 4, 5, 7, 7, 11, 20
 * */
public class MergingIterators {
    public static void main(String args[]) {
        Iterator A = new Iterator(1);
        A.next = new Iterator(3);
        A.next.next = new Iterator(7);

        Iterator B = new Iterator(1);
        B.next = new Iterator(2);
        B.next.next = new Iterator(5);
        B.next.next.next = new Iterator(7);
        B.next.next.next.next = new Iterator(11);
        B.next.next.next.next.next = new Iterator(20);

        Iterator C = new Iterator(0);
        C.next = new Iterator(4);

        Iterator output = getOutputIterator(List.of(A, B, C));
        while (output != null) {
            System.out.print(output.val + " , ");
            output = output.next;
        }
    }

    private static Iterator getOutputIterator(List<Iterator> list) {
        if (list == null)
            return null;
        PriorityQueue<Iterator> minHeap = new PriorityQueue<>((a, b) -> a.val - b.val);
        for (Iterator iterator : list) {
            if (iterator != null) {
                minHeap.add(iterator);
            }
        }
        Iterator head = null, curr = null;
        while (!minHeap.isEmpty()) {
            if (head == null) {
                head = minHeap.poll();
                curr = head;
            } else {
                curr.next = minHeap.poll();
                curr = curr.next;
            }
            if (curr.next != null) {
                minHeap.add(curr.next);
            }
        }
        return head;
    }
}

class Iterator {
    int val;
    Iterator next;

    public Iterator(int val) {
        this.val = val;
    }
}
