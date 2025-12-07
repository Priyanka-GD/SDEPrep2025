package googleOA;

import java.util.ArrayList;
import java.util.List;

public class Employee implements Comparable<Employee>{
    List<int[]> intervals;
    int lastEndTime;

    public Employee(int[] interval, int endTime) {
        this.intervals =  new ArrayList<>();
        this.intervals.add(interval);
        this.lastEndTime = endTime;
    }

    public void addInterval(int[] interval) {
        this.intervals.add(interval);
    }

    public void setLastEndTime(int time) {
        this.lastEndTime = time;
    }


    @Override
    public int compareTo(Employee other) {
        return this.lastEndTime - other.lastEndTime;
    }

    public String getValue() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        for (int[] interval : intervals) {
            stringBuilder.append("{" + interval[0] + " , " + interval[1] + "} ");
        }
        stringBuilder.append("]");

        return stringBuilder.toString();
    }
}