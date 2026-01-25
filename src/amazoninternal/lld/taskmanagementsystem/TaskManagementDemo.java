package amazoninternal.lld.taskmanagementsystem;

import java.util.List;

public class TaskManagementDemo {
    public static void main(String[] args) {
        TaskManager manager = new TaskManager();

        System.out.println("--- 1. Creating Task Hierarchy ---");
        // Create Parent Task
        Task mainProject = new Task("T1", "Launch E-commerce Site", Priority.CRITICAL);

        // Create Subtasks
        Task subtask1 = new Task("T1.1", "Setup Database", Priority.HIGH);
        Task subtask2 = new Task("T1.2", "Frontend Auth", Priority.MEDIUM);

        mainProject.addSubtask(subtask1);
        mainProject.addSubtask(subtask2);

        // Add to Manager
        manager.addTask("ProjectX", mainProject);
        manager.addTask("ProjectX", subtask1);
        manager.addTask("ProjectX", subtask2);

        System.out.println("\n--- 2. Testing Assignment & History ---");
        subtask1.setAssignee("Alice");
        subtask2.setAssignee("Bob");

        // View logs for subtask1
        subtask1.getActivityLog().forEach(log -> System.out.println(log.printLog()));

        System.out.println("\n--- 3. Testing Business Rule: Completion Constraint ---");
        try {
            System.out.println("Attempting to close Parent while subtasks are TODO...");
            mainProject.updateStatus(Status.DONE);
        } catch (IllegalStateException e) {
            System.out.println("Blocked: " + e.getMessage());
        }

        System.out.println("\n--- 4. Completing Subtasks ---");
        subtask1.updateStatus(Status.IN_PROGRESS);
        subtask1.updateStatus(Status.DONE);
        subtask2.updateStatus(Status.DONE);

        // Now closing parent should work
        mainProject.updateStatus(Status.DONE);
        System.out.println("Parent Task Status: " + mainProject.getStatus());

        System.out.println("\n--- 5. Testing Composable Filtering (AndFilter) ---");
        // Goal: Find tasks that are DONE AND assigned to Alice
        FilterStrategy aliceFilter = new AssigneeFilterStrategy("Alice");
        FilterStrategy doneFilter = new StatusFilterStrategy(Status.DONE);
        FilterStrategy compositeFilter = new AndFilterStrategy(aliceFilter, doneFilter);

        List<Task> results = manager.getTasks("ProjectX", compositeFilter);
        System.out.println("Found " + results.size() + " task(s) matching Alice + DONE:");
        results.forEach(t -> System.out.println(" - " + t.getTaskId() + ": " + t.getAssignee()));

        System.out.println("\n--- 6. Final Parent Task Activity Log ---");
        mainProject.getActivityLog().forEach(log -> System.out.println(log.printLog()));
    }
}