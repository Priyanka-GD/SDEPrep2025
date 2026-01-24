package amazoninternal.lld.autocompletesystem;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AlphabeticalStrategy implements SuggestionStrategy {
    @Override
    public List<String> rank(Map<String, Integer> candidates, int limit) {
        return candidates.keySet().stream()
                .limit(limit)
                .sorted()
                .collect(Collectors.toList());
    }
}
