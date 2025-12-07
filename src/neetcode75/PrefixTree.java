package neetcode75;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

class PrefixTree {
    TrieNode root;

    public PrefixTree() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode node = root;
        for (char ch : word.toCharArray()) {
            node.children.putIfAbsent(ch, new TrieNode());
            node = node.children.get(ch);
        }
        node.isEnd = true;
    }

    public TrieNode checkWord(String word) {
        TrieNode node = root;
        for (char ch : word.toCharArray()) {
            if (node.children.containsKey(ch))
                node = node.children.get(ch);
            else
                return null;
        }
        return node;
    }

    public void search(String word) {
        TrieNode node = checkWord(word);
        System.out.println(node != null && node.isEnd);
    }

    public void startsWith(String prefix) {
        TrieNode node = checkWord(prefix);
        System.out.println(node != null);
    }

    public static void main(String[] args) throws IOException {
        PrefixTree prefixTree = new PrefixTree();
        prefixTree.insert("dog");
        prefixTree.search("dog");    // return true
        prefixTree.search("do");     // return false
        prefixTree.startsWith("do"); // return true
        prefixTree.insert("do");
        prefixTree.search("do");
    }

}

class TrieNode {
    Map<Character, TrieNode> children = new HashMap<>();
    boolean isEnd = false;

    public TrieNode() {
    }
}
