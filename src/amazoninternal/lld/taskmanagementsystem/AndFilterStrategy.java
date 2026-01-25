package amazoninternal.lld.taskmanagementsystem;

import java.util.Arrays;
import java.util.List;

public class AndFilterStrategy implements FilterStrategy {
    private List<FilterStrategy> filters;
    public AndFilterStrategy(FilterStrategy... filterStrategies) {
        this.filters = Arrays.asList(filterStrategies);
    }
    /*
    * Initial State: result has 100 tasks.
    First Loop (StatusFilter - DONE):
    The StatusFilter looks at the 100 tasks and finds 20 that are DONE.
    result is now updated to those 20 tasks.
    Second Loop (AssigneeFilter - Alice):
    The AssigneeFilter looks only at those 20 DONE tasks.
    It finds that only 5 of those are assigned to Alice.
    result is now updated to those 5 tasks.
    Final Return: You get the 5 tasks that satisfy both conditions.
    * */
    @Override
    public List<Task> filter(List<Task> tasks) {
        List<Task> result = tasks;
        for (FilterStrategy filterStrategy : this.filters) {
            result = filterStrategy.filter(result);
        }
        return result;
    }
}
