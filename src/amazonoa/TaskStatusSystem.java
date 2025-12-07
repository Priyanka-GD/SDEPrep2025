package amazonoa;

import java.util.*;

public class TaskStatusSystem {
    enum Status {
        COMPLETE, BLOCKED, INCOMPLETE
    }

    public static void main(String[] args) {
        List<String> taskIds = Arrays.asList("A", "B", "C", "D", "E", "F");
        Map<String, String> parentMap = Map.of(
                "B", "A",
                "C", "A",
                "D", "B",
                "E", "B",
                "F", "C"
        );
        Map<String, Status> leafStatusMap = Map.of(
                "D", Status.COMPLETE,
                "E", Status.COMPLETE,
                "F", Status.INCOMPLETE
        );
        /*
                       A
                   /      \
                  B        C
                /   \     /
               D    E    F

         */
        Map<String, Status> result = determineStatus(parentMap, leafStatusMap, taskIds);
        for (Map.Entry<String, Status> entry : result.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }

    private static Map<String, Status> determineStatus(Map<String, String> parentMap, Map<String, Status> leafStatusMap,
                                                       List<String> taskIds) {


        Map<String, List<String>> parentToChildTasksMap = new HashMap<>();
        for (String childrenKey : parentMap.keySet()) {
            String parentKey = parentMap.get(childrenKey);
            parentToChildTasksMap.putIfAbsent(parentKey, new ArrayList<>());
            parentToChildTasksMap.get(parentKey).add(childrenKey);
        }
        Map<String, Status> taskStatus = new HashMap<>();
        for (String task : taskIds) {
                dfs(task, leafStatusMap, taskStatus, parentToChildTasksMap);
        }
        return taskStatus;

    }

    private static Status dfs(String task, Map<String, Status> leafStatusMap,
                              Map<String, Status> taskStatus, Map<String, List<String>> parentToChildTasksMap) {

        if (taskStatus.containsKey(task))
            return taskStatus.get(task);

        // If it is a leaf node
        if (!parentToChildTasksMap.containsKey(task) || parentToChildTasksMap.get(task).isEmpty()) {
            Status status = leafStatusMap.getOrDefault(task, Status.INCOMPLETE);
            taskStatus.put(task, status);
            return status;
        }

        boolean allComplete = true;
        for (String child : parentToChildTasksMap.get(task)) {
            Status status = dfs(child, leafStatusMap, taskStatus, parentToChildTasksMap);
            if (status == Status.BLOCKED) {
                taskStatus.put(task, Status.BLOCKED);
                return Status.BLOCKED;
            }
            if (status != Status.COMPLETE) {
                allComplete = false;
            }
        }

        Status finalStatus = allComplete ? Status.COMPLETE : Status.INCOMPLETE;
        taskStatus.put(task, finalStatus);
        return finalStatus;
    }

}
