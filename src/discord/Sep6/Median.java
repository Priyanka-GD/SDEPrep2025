package discord.Sep6;

import java.io.IOException;

public class Median {
    public static void main(String args[]) throws IOException {
        MedianDataStreamOne medianFinder = new MedianDataStreamOne();
        medianFinder.addNum(1);    // arr = [1]
        medianFinder.addNum(2);    // arr = [1, 2]
        medianFinder.findMedian(); // return 1.5 (i.e., (1 + 2) / 2)
        medianFinder.addNum(3);    // arr[1, 2, 3]
        System.out.println(medianFinder.findMedian());
        MedianDataStreamSecond medianFinder2 = new MedianDataStreamSecond();
        medianFinder2.addNum(1);    // arr = [1]
        medianFinder2.addNum(2);    // arr = [1, 2]
        medianFinder2.findMedian(); // return 1.5 (i.e., (1 + 2) / 2)
        medianFinder2.addNum(3);    // arr[1, 2, 3]
        System.out.println(medianFinder2.findMedian()); // return// return 2.0
    }
}
