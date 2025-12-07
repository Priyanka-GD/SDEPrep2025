package googleOA;

import java.util.*;


public class Main {
    public static void main(String[] args) {
        int arr[][] = {{2, 15}, {36, 45}, {9, 29}, {16, 23}, {4, 9}};
        List<int[]> arrayList = Arrays.asList(arr);
        minEmployeeCount(arrayList);

    }

    private static void printHeap(PriorityQueue<Employee> minHeap) {
        while (!minHeap.isEmpty()) {
            System.out.println(minHeap.poll().getValue());
        }
    }

    public static void minEmployeeCount(List<int[]> truckProcessingTimes) {
        Collections.sort(truckProcessingTimes, (a, b) -> a[0] - b[0]);
        PriorityQueue<Employee> minHeap = new PriorityQueue<>();
        for (int[] processingTime : truckProcessingTimes) {
            System.out.println("Trying to schedule work : " + processingTime[0] + "-" + processingTime[1]);
            if (!minHeap.isEmpty() && minHeap.peek().lastEndTime <= processingTime[0]) {
                Employee existingEmployee = minHeap.poll();
                System.out.println("Employee having earliest completion of work at : " + existingEmployee.lastEndTime);
                existingEmployee.addInterval(new int[]{processingTime[0], processingTime[1]});
                existingEmployee.setLastEndTime(processingTime[1]);
                System.out.println("Employee assigned new work : " + existingEmployee.getValue());
                minHeap.add(existingEmployee);
            } else {

                minHeap.add(new Employee(new int[]{processingTime[0], processingTime[1]}, processingTime[1]));
            }
        }

        printHeap(minHeap);
    }
}