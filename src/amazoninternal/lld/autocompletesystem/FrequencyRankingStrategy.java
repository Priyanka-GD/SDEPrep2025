package amazoninternal.lld.autocompletesystem;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FrequencyRankingStrategy implements SuggestionStrategy {
    @Override
    public List<String> rank(Map<String, Integer> candidates, int limit) {
        return candidates.entrySet().stream()
                // Sort by frequency descending, then alphabetically ascending
                .sorted((a, b) -> {
                    int freqCompare = b.getValue().compareTo(a.getValue());
                    return freqCompare != 0 ? freqCompare : a.getKey().compareTo(b.getKey());
                })
                .limit(limit)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }
}
