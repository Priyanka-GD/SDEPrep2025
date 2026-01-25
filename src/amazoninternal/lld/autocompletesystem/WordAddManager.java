package amazoninternal.lld.autocompletesystem;
import java.util.ArrayList;
import java.util.List;
public class WordAddManager {
    Trie rootNode;
    private static final int DEFAULT_LIMIT = 10;

    public  WordAddManager(){
        this.rootNode = new Trie();
    }

    public void insertWord(String word){
        String normalized = word.toLowerCase(); // Handle case-insensitivity here
        Trie currNode = rootNode;
        for (char ch : normalized.toCharArray()) {
            currNode = currNode.children.computeIfAbsent(ch, k -> new Trie());
            currNode.addWord(normalized);
        }
        System.out.println("Word Inserted : " + word);
    }

    // Overloaded method for default limit
    public List<String> getSuggestions(SuggestionStrategy strategy, String prefix) {
        return getSuggestions(strategy, prefix, DEFAULT_LIMIT);
    }

    public List<String> getSuggestions(SuggestionStrategy strategy, String prefix, int limit) {
        String normalized = prefix.toLowerCase();
        Trie currNode = rootNode;

        for (char ch : normalized.toCharArray()) {
            if (!currNode.children.containsKey(ch)) {
                return new ArrayList<>();
            }
            currNode = currNode.children.get(ch);
        }

        return strategy.rank(currNode.storedWordsFreqMap, limit);
    }
}
