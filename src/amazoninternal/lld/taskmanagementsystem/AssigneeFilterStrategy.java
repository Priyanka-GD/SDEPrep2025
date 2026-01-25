package amazoninternal.lld.taskmanagementsystem;

import java.util.List;
import java.util.stream.Collectors;

public class AssigneeFilterStrategy implements FilterStrategy {
    private String assignee;

    public AssigneeFilterStrategy(String assignee) {
        this.assignee = assignee;
    }

    @Override
    public List<Task> filter(List<Task> tasks) {
        return tasks.stream()
                .filter(task -> task.getAssignee() != null && task.getAssignee().equals(this.assignee))
                .collect(Collectors.toList());
    }
}
