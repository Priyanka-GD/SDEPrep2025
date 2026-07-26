package amazoninternal.codingassessment;

import java.util.HashMap;
import java.util.Map;

/*
 *
 * Problem DescriptionYou are working on the Product Detail Page team at Amazon.
 * As customers browse items across the site, the system needs to display a real-time list of their
 * $N$ Most Recently Viewed Products.To deliver a fast customer experience, you are tasked with designing
 * and implementing an in-memory caching data structure that maintains this list according to the following
 * requirements:Capacity Limit: The cache initialized with a fixed capacity $N$.
 * Access & Order Updates (get / View Item):Fetching an existing product's details returns its data and
 * moves it to the head (marking it as the most recently viewed product).If the product is not in the cache,
 * return a sentinel value (e.g., -1 or null).Insert & Update (put / Add Item):New Item: Insert the product at the head.
 * If the total number of unique items exceeds capacity $N$, evict (delete) the item at the tail (least recently viewed)
 * before inserting.Existing Item: Update the product's value/details and move it to the head without creating duplicate entries.
 * Efficiency Requirement: All operations (get and put) must run in $\mathcal{O}(1)$ average time complexity.
 *
 * */
public class LRUCache {

    private final int capacity;
    private final Map<Integer, Node> mapOfNodes;
    private final Node head;
    private final Node tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.mapOfNodes = new HashMap<>();

        // Dummy head and tail nodes to avoid null checks
        this.head = new Node(-1, -1);
        this.tail = new Node(-1, -1);
        this.head.next = tail;
        this.tail.prev = head;
    }

    // Adds a node right after the dummy head (Most Recently Used position)
    private void addToHead(Node node) {
        Node headNext = head.next;
        head.next = node;
        node.prev = head;
        node.next = headNext;
        headNext.prev = node;
    }

    // Removes an existing node from the doubly linked list
    private void deleteNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    // Moves an existing node to the Most Recently Used position
    private void moveToHead(Node node) {
        deleteNode(node);
        addToHead(node);
    }

    public int get(int key) {
        if (!mapOfNodes.containsKey(key)) {
            return -1;
        }
        Node node = mapOfNodes.get(key);
        moveToHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        if (mapOfNodes.containsKey(key)) {
            Node node = mapOfNodes.get(key);
            node.value = value; // Update value
            moveToHead(node);
        } else {
            if (mapOfNodes.size() == capacity) {
                // Evict Least Recently Used item (node right before dummy tail)
                Node lruNode = tail.prev;
                deleteNode(lruNode);
                mapOfNodes.remove(lruNode.key);
            }
            Node newNode = new Node(key, value);
            addToHead(newNode);
            mapOfNodes.put(key, newNode);
        }
    }

    private static class Node {
        int key;
        int value;
        Node prev;
        Node next;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    // Main Test Harness
    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);

        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println("get(1): " + cache.get(1)); // Expected: 1

        cache.put(3, 3);                              // Evicts key 2
        System.out.println("get(2): " + cache.get(2)); // Expected: -1

        cache.put(4, 4);                              // Evicts key 1
        System.out.println("get(1): " + cache.get(1)); // Expected: -1
        System.out.println("get(3): " + cache.get(3)); // Expected: 3
        System.out.println("get(4): " + cache.get(4)); // Expected: 4
    }
}