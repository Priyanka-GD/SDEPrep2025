package cogbee;

public  class EmployeeNode {
    int employeeId;
    String empName;
    public EmployeeNode left;
    public EmployeeNode right;

    public EmployeeNode(int employeeId, String empName, EmployeeNode left, EmployeeNode right) {
        this.employeeId = employeeId;
        this.empName = empName;
        this.left = left;
        this.right = right;
    }
}
