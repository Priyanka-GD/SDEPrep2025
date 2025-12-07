package neetcode75;

import DAO.ListNode;

//https://leetcode.com/problems/reorder-list/
public class ReorderList {
    public void reorderList(ListNode head) {
        if (head == null || head.next == null) {
            return; // No need to reorder if the list is empty or has only one node
        }
        // head [1,2,3,4,5,6], [1,2,3,4], [1,6,5,4], [1,6,2,3,4], [1,6,2,5,4],

        // Step 1: Find the middle of the linked list (using slow and fast pointers)
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode prev = null, curr = slow, tmp;
        while (curr != null) {
            tmp = curr.next;

            curr.next = prev;
            prev = curr;
            curr = tmp;
        }
        // head [1,2,3,4], [1,6,5,4], [1,6,2,3,4], [1,6,2,5,4],[1,6,2,5,3,4]
        // Step 3: Interleave the two halves
        ListNode first = head; // [1,2,3,4]
        ListNode second = prev; // [6,5,4]
        while (second.next != null) {
            tmp = first.next; // [2,3,4], [3,4]
            first.next = second; // [1,6,5,4], [3,5,4]
            first = tmp;// [2,3,4],[3,4],

            tmp = second.next;// [5,4], [4]
            second.next = first;// [6,2,3,4], [5,3,4]
            second = tmp;// [5,4], [4]
        }
    }
}

