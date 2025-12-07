package neetcode75;
//https://leetcode.com/problems/remove-nth-node-from-end-of-list/
import DAO.ListNode;

public class RemoveNNode {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        int len = 0;
        ListNode curr = head;
        while (curr != null) {
            curr = curr.next;
            len++;
        }
        len -= n;
        ListNode resultNode = new ListNode(-1);
        resultNode.next = head;
        curr = resultNode;
        while (len > 0) {
            len--;
            curr = curr.next;
        }
        curr.next = curr.next.next;
        return resultNode.next;
    }
}
