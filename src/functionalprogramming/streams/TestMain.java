package functionalprogramming.streams;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TestMain {
    public static  void main(String[] args) {
        List<Student> studentList = new ArrayList<>();
        List<Department> departmentList = new ArrayList<>();

        // Mapping department IDs to the number of students
        Map<Integer, Long> deptStudentCount = studentList.stream()
                .flatMap(student -> student.deptIds.stream()) // Flatten department IDs
                .collect(Collectors.groupingBy(deptId -> deptId, Collectors.counting()));

        // Filtering departments with at least two students
        List<Department> departmentsWithAtLeastTwoStudents = departmentList.stream()
                .filter(d -> deptStudentCount.getOrDefault(d.departmentId, 0L) >= 2)
                .collect(Collectors.toList());

        // Printing the result
        departmentsWithAtLeastTwoStudents.forEach(d ->
                System.out.println("Department: " + d.deptName + " (ID: " + d.departmentId + ")"));
    }
}


class Student {
    Integer id;
    String name;
    List<Integer> deptIds;

    public Student(Integer id, String name, List<Integer> deptIds) {
        this.id = id;
        this.name = name;
        this.deptIds = deptIds;
    }
}

class Department {
    Integer departmentId;
    String deptName;

    public Department(Integer departmentId, String deptName) {
        this.departmentId = departmentId;
        this.deptName = deptName;
    }
}

