package ziprecruiter;

import java.util.*;
//https://leetcode.com/problems/alien-dictionary/description/
public class AlienDictionary {

    class Solution {
        public String alienOrder(String[] words) {
            Map<Character, List<Character>> adjList = new HashMap<>();
            Map<Character, Integer> counts = new HashMap<>();
            for (String word : words) {
                for (char ch : word.toCharArray()) {
                    counts.put(ch, 0);
                    adjList.put(ch, new ArrayList<>());
                }
            }

            // Find edges
            for (int idx = 0; idx < words.length - 1; idx++) {
                String word1 = words[idx];
                String word2 = words[idx + 1];
                int len1 = word1.length();
                int len2 = word2.length();

                if (len1 > len2
                        && word1.startsWith(word2))
                    return "";

                for (int i = 0; i < Math.min(len1, len2); i++) {
                    if (word1.charAt(i) != word2.charAt(i)) {
                        adjList.get(word1.charAt(i)).add(word2.charAt(i));
                        counts.put(word2.charAt(i), counts.get(word2.charAt(i)) + 1);
                        break;
                    }
                }
            }

            StringBuilder string = new StringBuilder();
            Queue<Character> queue = new LinkedList<>();

            for (char ch : counts.keySet()) {
                if (counts.get(ch).equals(0))
                    queue.add(ch);
            }

            while (!queue.isEmpty()) {
                //remove edges that have no edge going inwards
                char ch = queue.poll();
                string.append(ch);
                for (char next : adjList.get(ch)) {
                    counts.put(next, counts.get(next) - 1);
                    if (counts.get(next).equals(0))
                        queue.add(next);
                }
            }
            if (string.length() < counts.size()) {
                return "";
            }
            return string.toString();
        }
    }
}
