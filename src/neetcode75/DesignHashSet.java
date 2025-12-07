package neetcode75;

import java.io.IOException;

public class DesignHashSet {
    private final int BUCKET_COUNT = 1000;
    private Bucket[] buckets;

    public DesignHashSet() {
        buckets = new Bucket[BUCKET_COUNT];
        for (int i = 0; i < BUCKET_COUNT; i++)
            buckets[i] = new Bucket();
    }

    public int hashCode(int key) {
        return key % BUCKET_COUNT;
    }

    public void add(int key) {
        int idx = hashCode(key);
        buckets[idx].add(key);

    }

    public void remove(int key) {
        int idx = hashCode(key);
        buckets[idx].remove(key);
    }

    public boolean contains(int key) {
        int idx = hashCode(key);
        return buckets[idx].contains(key);
    }

    public static void main(String args[]) throws IOException {
        DesignHashSet designHashSet = new DesignHashSet();
        designHashSet.add(1);
        designHashSet.add(2);
        System.out.println("Contains : " + designHashSet.contains(1));
        System.out.println("Contains : " + designHashSet.contains(3));
        designHashSet.add(2);
        System.out.println("Contains : " + designHashSet.contains(2));
        designHashSet.remove(2);
        System.out.println("Contains : " + designHashSet.contains(2));
    }
}

class Bucket {
    private LLNode head;

    public Bucket() {
        head = new LLNode(-1); // Dummy node to simplify insert/delete operations
    }

    public void add(int key) {
        if (!contains(key)) {
            LLNode newNode = new LLNode(key);
            newNode.next = head.next;
            head.next = newNode;
        }
    }

    public void remove(int key) {
        LLNode prev = head;
        while (prev.next != null) {
            if (prev.next.val == key) {
                prev.next = prev.next.next;
                return;
            }
            prev = prev.next;
        }
    }

    public boolean contains(int key) {
        LLNode curr = head.next;
        while (curr != null) {
            if (curr.val == key) {
                return true;
            }
            curr = curr.next;
        }
        return false;
    }
}

class LLNode {
    int val;
    LLNode next;

    public LLNode(int val) {
        this.val = val;
        this.next = null;
    }
}

