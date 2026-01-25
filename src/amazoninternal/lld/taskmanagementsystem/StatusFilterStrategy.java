package amazoninternal.lld.taskmanagementsystem;

import java.util.List;
import java.util.stream.Collectors;

public class StatusFilterStrategy implements FilterStrategy{
    private Status status;
    public StatusFilterStrategy(Status status){
        this.status = status;
    }
    @Override
    public List<Task> filter(List<Task> tasks) {
        return tasks.stream().filter(task -> task != null && task.getStatus() == this.status).collect(Collectors.toList());
    }
}
