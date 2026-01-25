package amazoninternal.lld.taskmanagementsystem;

import java.util.ArrayList;
import java.util.List;

public class Task {
    private String assignee;
    private String taskId;
    private Status status;
    private Priority priority;
    private List<Task> subtasks;

    private String title;
    private List<ActivityLog> activityLog;

    public Task(String taskId, String title, Priority priority) {
        this.taskId = taskId;
        this.title = title;
        this.priority = priority;
        this.subtasks = new ArrayList<>(); // Crucial
        this.activityLog = new ArrayList<>(); // Crucial
        this.status = Status.TODO;
        this.activityLog.add(new ActivityLog("Parent Task with id : " + taskId + " is created."));
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
        this.activityLog.add(new ActivityLog("Assignee set to : " + assignee));
    }

    public void updateStatus(Status newStatus) {
        // 1. VALIDATE FIRST
        if (newStatus == Status.DONE && !this.subtasks.isEmpty()) {
            if (!checkAllSubTaskStatus()) {
                throw new IllegalStateException("Cannot complete: subtasks are pending.");
            }
        }
        // 2. MUTATE SECOND (Only if validation passed)
        this.status = newStatus;
        this.activityLog.add(new ActivityLog("Status updated to : " + newStatus));
        // Optional: Special log for completion
        if (newStatus == Status.DONE) {
            this.activityLog.add(new ActivityLog("Parent Task " + taskId + " marked as COMPLETED."));
        }
    }

    private boolean checkAllSubTaskStatus() {
        for (Task subTask : this.subtasks) {
            if (subTask.getStatus() != Status.DONE)
                return false;
        }
        return true;
    }

    public String getAssignee() {
        return this.assignee;
    }

    public String getTaskId() {
        return this.taskId;
    }

    public Status getStatus() {
        return this.status;
    }

    public Priority getPriority() {
        return this.priority;
    }

    public List<Task> getSubtasks() {
        return this.subtasks;
    }

    public List<ActivityLog> getActivityLog() {
        return this.activityLog;
    }

    public void addSubtask(Task subTask) {
        this.subtasks.add(subTask);
        activityLog.add(new ActivityLog("Subtask added: " + subTask.getTaskId()));
    }
}
