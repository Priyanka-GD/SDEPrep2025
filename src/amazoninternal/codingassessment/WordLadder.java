package amazoninternal.codingassessment;

import java.util.*;

public class WordLadder {

    public static void main(String args[]) {
        List<String> wordList = Arrays.asList("hot", "dot", "dog", "lot", "log", "cog");
        System.out.println("Ladder length is : " + ladderLength("hit", "cog", wordList));
    }

    public static Map<String, List<String>> getMappedWords(List<String> wordList) {
        Map<String, List<String>> wrappedWordsMap = new HashMap<>();
        for (String word : wordList) {
            for (int idx = 0; idx < word.length(); idx++) {
                String combination = word.substring(0, idx) + "*" + word.substring(idx + 1);
                wrappedWordsMap.putIfAbsent(combination, new ArrayList<>());
                wrappedWordsMap.get(combination).add(word);
            }
        }
        return wrappedWordsMap;
    }

    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Map<String, List<String>> wrappedWordsMap = getMappedWords(wordList);

        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(beginWord, 1));
        Set<String> visited = new HashSet<>();

        while (!queue.isEmpty()) {
            Pair currPair = queue.poll();
            int currLevel = currPair.level;
            String currWord = currPair.word;

            for (int idx = 0; idx < currWord.length(); idx++) {
                String combination = currWord.substring(0, idx) + "*" + currWord.substring(idx + 1);
                for (String word : wrappedWordsMap.getOrDefault(combination, new ArrayList<>())) {
                    if (!visited.contains(word)) {
                        if (word.equals(endWord))
                            return currLevel + 1;
                        visited.add(word);
                        queue.add(new Pair(word, currLevel + 1));
                    }
                }
            }
        }
        return 0;
    }
}

class Pair {
    String word;
    int level;

    public Pair(String str, int level) {
        this.word = str;
        this.level = level;
    }
}
