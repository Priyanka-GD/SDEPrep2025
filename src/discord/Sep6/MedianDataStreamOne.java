package discord.Sep6;

import java.util.ArrayList;
import java.util.List;

/*This method would work well when the amount of insertion
queries is lesser or about the same as the amount of median finding queries.*/
public class MedianDataStreamOne {
    List<Integer> listOfIntegers;
    int size;

    public MedianDataStreamOne() {
        listOfIntegers = new ArrayList<>();
        size = 0;
    }

    public void addNum(int num) {
        int findIdxToInsert = binarySearch(num);
        listOfIntegers.add(findIdxToInsert, num);
        size++;
    }

    public double findMedian() {
        if (size % 2 == 0) {
            return (listOfIntegers.get(size / 2)
                    + listOfIntegers.get(size / 2 - 1)) / 2.0;
        } else {
            return listOfIntegers.get(size / 2);
        }
    }

    private int binarySearch(int num) {
        int low = 0;
        int high = listOfIntegers.size();

        while (low < high) {
            int mid = low + (high - low) / 2;
            if (listOfIntegers.get(mid) >= num) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }
}
