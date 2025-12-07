package DAO;

public class Bucket {
    HashMapNode head;

    public Bucket() {
        head = new HashMapNode(-1, -1);
    }

    private boolean contains(int key) {
        HashMapNode currHashMapNode = head;
        while (currHashMapNode != null) {
            if (currHashMapNode.key == key)
                return true;
            currHashMapNode = currHashMapNode.next;
        }
        return false;
    }

    public void remove(int key) {
        HashMapNode currHashMapNode = head;
        while (currHashMapNode.next != null) {
            if (currHashMapNode.next.key == key) {
                currHashMapNode.next = currHashMapNode.next.next;
                return;
            } else
                currHashMapNode = currHashMapNode.next;
        }
    }

    public void put(int key, int val) {
        if (contains(key))
            remove(key);
        HashMapNode newHashMapNode = new HashMapNode(key, val);
        newHashMapNode.next = head.next;
        head.next = newHashMapNode;
    }

    public int get(int key) {
        if (contains(key)) {
            HashMapNode currHashMapNode = head;
            while (currHashMapNode != null) {
                if (currHashMapNode.key == key)
                    return currHashMapNode.val;
                currHashMapNode = currHashMapNode.next;
            }
        }
        return -1;
    }
}
