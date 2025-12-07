package amazonoa;

import java.util.*;
/*
Problem Statement: First and Last Unique Words in a Stream
You are given a continuous stream of strings, where each string consists of multiple space-separated
words made up of lowercase English letters.

As the stream progresses, you need to process each word in the order they appear (from left to right,
across all strings). For every new word, print the first and last unique words seen so far in the stream.

A word is considered unique if it has appeared exactly once up to that point in the stream.

🔹 Input:
A continuous stream of strings s1, s2, ..., each containing words separated by spaces.

All words contain only lowercase English letters.

🔹 Output:
For every word processed, output a pair [firstUniqueWord, lastUniqueWord] representing:

The first word that is still unique (first seen only once)

The last word that is still unique (most recently seen only once)

📌 Example:
Input:
java
Copy
Edit
s1 = "my name is xyz";
s2 = "my name is fff";
Word stream:
["my", "name", "is", "xyz", "my", "name", "is", "fff"]

Output:
csharp
Copy
Edit
[my, my]
[my, name]
[my, is]
[my, xyz]
[name, xyz]
[is, xyz]
[xyz, xyz]
[xyz, fff]
🔸 Notes:
Once a word appears more than once, it is no longer unique and should be excluded from future outputs.

If no unique words remain, you may return empty strings (["", ""]) or handle as per requirements.
*/

public class UniqueWordsCombination {
    static Node head;
    static Node tail;

    static Map<String, Node> map;

    public static void main(String[] args) {
        String s1 = "my name is xyz";
        String s2 = "my name is fff";
        map = new HashMap<>();
        head = new Node("null");
        tail = new Node("null");
        head.next = tail;
        tail.prev = head;
        printOutput(newWord(s1));
        printOutput(newWord(s2));
    }

    private static void printOutput(List<String[]> result) {
        for (String[] pair : result) {
            System.out.println(Arrays.toString(pair));
        }
    }

    private static List<String[]> newWord(String str) {
        String strArr[] = str.split("\\s+");
        List<String[]> output = new ArrayList<>();
        for (String part : strArr) {
            if (map.containsKey(part)) {
                Node node = map.get(part);
                removeNode(node);
                map.remove(part);
            } else {
                Node newNode = new Node(part);
                insertNode(newNode);
                map.put(part, newNode);
            }
            output.add(new String[]{head.next.key, tail.prev.key});
        }
        return output;
    }

    private static void insertNode(Node newNode) {
        Node temp = tail.prev;
        tail.prev = newNode;
        newNode.next = tail;
        newNode.prev = temp;
        temp.next = newNode;
    }

    private static void removeNode(Node node) {
        node.next.prev = node.prev;
        node.prev.next = node.next;
    }

}

class Node {
    String key;
    Node prev;
    Node next;

    public Node(String key) {
        this.key = key;
    }
}
