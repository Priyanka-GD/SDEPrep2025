package amazoninternal.lld.autocompletesystem;

import java.util.HashMap;
import java.util.Map;

public class Trie {
    Map<Character, Trie> children;

    Map<String, Integer> storedWordsFreqMap;

    public Trie(){
        this.storedWordsFreqMap = new HashMap<>();
        this.children = new HashMap<>();
    }

    public void addWord(String word){
        this.storedWordsFreqMap.put(word, getFreqOfWord(word) + 1);
    }

    public int getFreqOfWord(String word){
        return this.storedWordsFreqMap.getOrDefault(word, 0);
    }
}
