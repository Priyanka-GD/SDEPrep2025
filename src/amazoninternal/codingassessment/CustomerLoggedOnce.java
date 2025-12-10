package amazoninternal.codingassessment;

import java.util.HashMap;
import java.util.Map;

/*
* Problem Statement
* Let's say we are working on a shopping website and we want to analyze non returning users to offer them some promotions to get their attention again.
We know that every user has their unique usernames, so we can track whether they are visiting the website again or not.
For this task we need to implement below methods.
	•	newUserLogin(username):This method will be called every time a user logs in
	•	getOldestOneTimeVisitingUser():This method will return the username of the oldest customer who has visited the website only once.
Example:
	•	newUserLogin(john);
	•	newUserLogin(jeff);
	•	newUserLogin(jeff);
	•	getOldestOneTimeVisitingUser();should return john
	•	newUserLogin(chriss);
	•	newUserLogin(john);
	•	newUserLogin(adam);
	•	newUserLogin(sandy);
	•	getOldestOneTimeVisitingUser();should return chriss

* */
public class CustomerLoggedOnce {
    static Map<String, Integer> visitCount = new HashMap<>();
    static Map<String, Node> onceOnlyNodes = new HashMap<>(); // only users with count == 1
    static Node head = new Node("head");
    static Node tail = new Node("tail");

    public static void main(String args[]) {
        head.next = tail;
        tail.prev = head;
        newUserLogin("john");
        newUserLogin("jeff");
        newUserLogin("jeff");
        System.out.println(getOldestOneTimeVisitingUser()); // john
        newUserLogin("chriss");
        newUserLogin("john");
        newUserLogin("adam");
        newUserLogin("sandy");
        System.out.println(getOldestOneTimeVisitingUser()); // chriss
    }

    public static void newUserLogin(String name) {
        int prevCount = visitCount.getOrDefault(name, 0);
        int newCount = prevCount + 1;
        visitCount.put(name, newCount);

        if (newCount == 1) {
            // First time we see this user -> add to DLL of one-timers
            Node newNode = new Node(name);
            addAtHead(newNode); // or at tail if you want; we’ll read oldest from other end
            onceOnlyNodes.put(name, newNode);
        } else if (newCount == 2) {
            // User is no longer one-time -> remove from DLL
            Node node = onceOnlyNodes.get(name);
            if (node != null) {
                removeNode(node);
                onceOnlyNodes.remove(name);
            }
            // For count > 2, nothing to do: they’re already not in the one-time list.
        }
        // If newCount > 2, we just ignore for DLL; count is tracked in visitCount.
    }

    private static void addAtHead(Node node) {
        Node currHead = head.next;
        head.next = node;
        node.prev = head;
        node.next = currHead;
        currHead.prev = node;
    }

    private static void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    public static String getOldestOneTimeVisitingUser() {
        // Oldest is at tail.prev (if you insert new ones at head)
        if (tail.prev == head) {
            return null; // or "" or throw exception – no one-time visitors exist
        }
        return tail.prev.name;
    }


}

class Node {
    String name;
    Node next;
    Node prev;

    public Node(String name) {
        this.name = name;
    }
}
