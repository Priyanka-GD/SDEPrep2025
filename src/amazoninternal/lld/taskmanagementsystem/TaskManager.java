package amazoninternal.lld.taskmanagementsystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaskManager {
    Map<String, List<Task>> projectLists = new HashMap<>();

    public void addTask(String projectName, Task task) {
        projectLists.putIfAbsent(projectName, new ArrayList<>());
        projectLists.get(projectName).add(task);
    }

    public List<Task> getTasks(String projectName, FilterStrategy filterStrategy) {
        List<Task> tasks = projectLists.getOrDefault(projectName, new ArrayList<>());
        return filterStrategy == null ? tasks : filterStrategy.filter(tasks);
    }
}
