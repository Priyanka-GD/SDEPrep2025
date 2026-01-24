package amazoninternal.lld.autocompletesystem;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface SuggestionStrategy {
    List<String> rank(Map<String, Integer> candidates, int limit);
}
