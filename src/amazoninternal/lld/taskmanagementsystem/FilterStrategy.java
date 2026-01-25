package amazoninternal.lld.taskmanagementsystem;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public interface FilterStrategy {
    List<Task> filter(List<Task> tasks);
}

