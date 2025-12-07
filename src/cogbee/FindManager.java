package cogbee;

import java.util.Arrays;

public class FindManager {
    public static void main(String[] args) {
        //employee id --> managerId --> for any two employees closest manager id
        //          ceo
        // /                       \
        // manager1               manager2
        //        emp 1, emp2     emp3. emp4
        EmployeeNode emp1 = new EmployeeNode(234, "YXZ", null, null);
        EmployeeNode emp2 = new EmployeeNode(345, "UYT", null, null);
        EmployeeNode emp3 = new EmployeeNode(567, "UIT", null, null);
        EmployeeNode emp4 = new EmployeeNode(897, "XVY", null, null);

        EmployeeNode manager1 = new EmployeeNode(436, "NHG", emp1, emp2);
        EmployeeNode manager2 = new EmployeeNode(834, "XDF", emp3, emp4);
        EmployeeNode ceo = new EmployeeNode(123, "XYZ", manager1, manager2);

        System.out.println("Get Closest Manager " + getClosestManager(ceo, emp1, emp4).empName);
    }


    private static EmployeeNode getClosestManager(EmployeeNode managerNode, EmployeeNode emp1, EmployeeNode emp2) {
        if (managerNode == null || managerNode == emp1 || managerNode == emp2)
            return managerNode;
        EmployeeNode leftEmpNode = getClosestManager(managerNode.left, emp1, emp2);
        EmployeeNode rightEmpNode = getClosestManager(managerNode.right, emp1, emp2);

        if (leftEmpNode != null && rightEmpNode != null)
            return managerNode;

        if (leftEmpNode == null)
            return rightEmpNode;
        else
            return leftEmpNode;
    }
}
