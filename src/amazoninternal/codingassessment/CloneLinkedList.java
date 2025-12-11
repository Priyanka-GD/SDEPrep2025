package amazoninternal.codingassessment;

import java.util.HashMap;
import java.util.Map;

public class CloneLinkedList {
    static Map<LinkedNode, LinkedNode> map;

    public static void main(String args[]) {
        map = new HashMap<>();
        // Create nodes
        LinkedNode n1 = new LinkedNode(1);
        LinkedNode n2 = new LinkedNode(2);
        LinkedNode n3 = new LinkedNode(3);

        // Set next pointers
        n1.next = n2;
        n2.next = n3;

        // Set random pointers
        n1.random = n3; // 1 → 3
        n2.random = n1; // 2 → 1
        n3.random = n2; // 3 → 2

        CloneLinkedList cl = new CloneLinkedList();
        LinkedNode clonedHead = cl.copyRandomList(n1);

        System.out.println("Original List:");
        printList(n1);

        System.out.println("\nCloned List:");
        printList(clonedHead);
    }

    public static void printList(LinkedNode head) {
        LinkedNode temp = head;
        while (temp != null) {
            int randomVal = temp.random == null ? -1 : temp.random.val;
            System.out.println("Node: " + temp.val + " | Random: " + randomVal);
            temp = temp.next;
        }
    }

    public LinkedNode copyRandomList(LinkedNode head) {
        if (head == null)
            return null;
        if (map.containsKey(head)) {
            return map.get(head);
        }
        LinkedNode newNode = new LinkedNode(head.val);
        map.put(head, newNode);
        newNode.next = copyRandomList(head.next);
        newNode.random = copyRandomList(head.random);
        return newNode;
    }
}

class LinkedNode {
    int val;
    LinkedNode next;
    LinkedNode random;

    public LinkedNode(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
