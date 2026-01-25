package amazoninternal.lld.autocompletesystem;

import java.util.List;

public class AutocompleteDemo {
    public static void main(String[] args) {
        WordAddManager autocompleteSystem = new WordAddManager();

        // 1. Setup Ranking Strategies
        SuggestionStrategy frequencyStrategy = new FrequencyRankingStrategy();
        SuggestionStrategy alphabeticalStrategy = new AlphabeticalStrategy();

        // 2. Insert Words Dynamically
        // Notice we insert "apple" multiple times to increase its frequency
        autocompleteSystem.insertWord("apple");
        autocompleteSystem.insertWord("apple");
        autocompleteSystem.insertWord("apply");
        autocompleteSystem.insertWord("apple");
        autocompleteSystem.insertWord("application");
        autocompleteSystem.insertWord("ball");
        autocompleteSystem.insertWord("app");

        System.out.println("\n--- Suggestions for 'app' (Frequency Strategy, Limit 2) ---");
        List<String> freqResults = autocompleteSystem.getSuggestions(frequencyStrategy, "app", 2);
        freqResults.forEach(System.out::println);
        // Expected: apple (3), apply (1)

        System.out.println("\n--- Suggestions for 'appl' (Alphabetical Strategy, Limit 5) ---");
        List<String> alphaResults = autocompleteSystem.getSuggestions(alphabeticalStrategy, "appl", 5);
        alphaResults.forEach(System.out::println);
        // Expected: apple, application, apply

        System.out.println("\n--- Handling Case Insensitivity ('APP') ---");
        List<String> caseResults = autocompleteSystem.getSuggestions(frequencyStrategy, "APP");
        caseResults.forEach(System.out::println);
    }
}