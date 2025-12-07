package cogbee;

import java.util.HashMap;
import java.util.Map;

class LRUCache {
    Map<Integer, Node> nodeToKeyMap;
    Node head;
    Node tail;
    int capacity;

    public LRUCache() {
        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        head.next = tail;
        tail.prev = head;
        nodeToKeyMap = new HashMap<>(capacity);
        this.capacity = 10;
    }

    public void updateNode(Node newNode) {
        newNode.prev = head;
        newNode.next = head.next;
        newNode.next.prev = newNode;
        head.next = newNode;
    }

    public void removeNode(Node toRemove) {
        toRemove.prev.next = toRemove.next;
        toRemove.next.prev = toRemove.prev;
    }

    public int get(int key) {
        if (nodeToKeyMap.containsKey(key)) {
            Node node = nodeToKeyMap.get(key);
            removeNode(node);
            updateNode(node);
            return node.val;
        }

        return -1;
    }

    public void put(int key, int value) {
        if (nodeToKeyMap.containsKey(key)) {
            Node existsNode = nodeToKeyMap.get(key);
            removeNode(existsNode);
        }

        Node newNode = new Node(key, value);
        updateNode(newNode);
        nodeToKeyMap.put(key, newNode);
        if (nodeToKeyMap.size() > capacity) {
            Node toRemove = tail.prev;
            removeNode(toRemove);
            nodeToKeyMap.remove(toRemove.key);
        }
    }
}

class Node {
    int key;
    int val;
    Node prev;
    Node next;

    public Node(int key, int val) {
        this.key = key;
        this.val = val;
    }
}

    // 1<-->2<-->3<-->4  1<-->3<-->4
    //2.prev = 3.prev;
    //2.prev.next = 2.next
    // -1<-->2<-->-1
    // 2.prev = -1;
    // 2.next = -1;
    //tail -1.prev = 2;
