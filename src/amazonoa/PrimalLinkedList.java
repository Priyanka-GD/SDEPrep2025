package amazonoa;

/*
* 11. Rearrange Linked List by Primality
Move:
Prime numbers first
Non-prime numbers later
Example:
Input:
4 → 2 → 3 → 8 → 9 → 11 → 15
Output:
2 → 3 → 11 → 4 → 8 → 9 → 15
* */
public class PrimalLinkedList {

    public static void main(String[] args) {
        int[] values = {4, 2, 3, 8, 9, 11, 15};
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        for (int val : values) {
            curr.next = new ListNode(val);
            curr = curr.next;
        }

        ListNode result = transformLinkedList(dummy.next);
        printList(result);
    }

    public static ListNode transformLinkedList(ListNode head) {
        // Two dummy heads to maintain the prime and non-prime lists
        ListNode primeDummy = new ListNode(0);
        ListNode nonPrimeDummy = new ListNode(0);

        ListNode primeTail = primeDummy;
        ListNode nonPrimeTail = nonPrimeDummy;
        ListNode curr = head;

        while (curr != null) {
            if (isPrime(curr.val)) {
                primeTail.next = curr;
                primeTail = primeTail.next;
            } else {
                nonPrimeTail.next = curr;
                nonPrimeTail = nonPrimeTail.next;
            }
            curr = curr.next;
        }

        // Cut off trailing references from the non-prime list to avoid cycles
        nonPrimeTail.next = null;

        // Connect the end of the prime list to the start of the non-prime list
        primeTail.next = nonPrimeDummy.next;

        return primeDummy.next;
    }

    public static boolean isPrime(int n) {
        if (n <= 1) return false;
        for (int div = 2; div * div <= n; div++) {
            if (n % div == 0) return false;
        }
        return true;
    }

    private static void printList(ListNode head) {
        ListNode curr = head;
        while (curr != null) {
            System.out.print(curr.val + (curr.next != null ? " -> " : ""));
            curr = curr.next;
        }
        System.out.println();
    }
}

class ListNode {
    int val;
    ListNode next;

    public ListNode(int val) {
        this.val = val;
    }
}
