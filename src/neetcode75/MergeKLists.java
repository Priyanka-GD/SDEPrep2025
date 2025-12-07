package neetcode75;

import DAO.ListNode;

import java.util.PriorityQueue;
//https://leetcode.com/problems/merge-k-sorted-lists/
public class MergeKLists {
    public ListNode mergeKLists(ListNode[] lists) {

        PriorityQueue<ListNode> minHeap = new PriorityQueue<>((a, b) -> Integer.compare(a.val, b.val));

        for(ListNode list : lists)
            minHeap.add(list);

        ListNode head = null, curr = null;

        while(!minHeap.isEmpty())
        {
            if(head == null)
            {
                head = minHeap.poll();
                curr = head;
            }
            else
            {
                curr.next = minHeap.poll();
                curr = curr.next;
            }
            if (curr.next != null)
                minHeap.add(curr.next);
        }
        return head;
    }
}
