package discord;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//https://leetcode.com/problems/find-and-replace-pattern/
public class FindReplacePattern {
    public List<String> findAndReplacePattern(String[] words, String pattern) {
        List<String> result = new ArrayList<>();
        for (String word : words) {
            Map<Character, Character> wordMap = new HashMap<>();
            Map<Character, Character> patternMap = new HashMap<>();
            boolean patternMatch = true;

            for (int idx = 0; idx < word.length(); idx++) {
                char wordChar = word.charAt(idx);
                char patternChar = pattern.charAt(idx);

                if (wordMap.containsKey(wordChar)) {
                    if (wordMap.get(wordChar) != patternChar)
                        patternMatch = false;
                } else
                    wordMap.put(wordChar, patternChar);
                if (patternMap.containsKey(patternChar)) {
                    if (patternMap.get(patternChar) != wordChar)
                        patternMatch = false;
                } else
                    patternMap.put(patternChar, wordChar);
            }
            if (patternMatch)
                result.add(word);
        }
        return result;
    }
}
