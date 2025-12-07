package discord.August27;

import DAO.TrieNode;

import java.io.IOException;
import java.util.*;

public class ConcatenatedWords {
    static TrieNode root = new TrieNode();
    static List<String> wordList = new ArrayList<>();

    public static void main(String args[]) throws IOException {
        String words[] = {"cat", "cats", "catsdogcats", "dog", "dogcatsdog", "hippopotamuses", "rat", "ratcatdogcat"};
        findAllConcatenatedWordsInADict(words);
        for (String word : wordList)
            System.out.println(word);
    }

    private static void findAllConcatenatedWordsInADict(String[] words) {

        for (String word : words) {
            if (word.length() > 0)
                insertWordInTrie(word);
        }

        for (String word : words) {
            if (word.length() > 0 && canForm(word, 0, 0))
                wordList.add(word);
        }
    }

    private static boolean canForm(String word, int index, int count) {
        if (index == word.length())
            return count >= 2;
        TrieNode current = root;
        for (int idx = index; idx < word.length(); idx++) {
            char ch = word.charAt(idx);
            if (!current.children.containsKey(ch))
                break;
            current = current.children.get(ch);
            if (current.isEnd) {
                if (canForm(word, idx + 1, count + 1))
                    return true;
            }
        }
        return false;
    }

    private static void insertWordInTrie(String word) {
        TrieNode node = root;
        for (int idx = 0; idx < word.length(); idx++) {
            char ch = word.charAt(idx);
            node.children.putIfAbsent(ch, new TrieNode());
            node = node.children.get(ch);
        }
        node.isEnd = true;
    }
}
