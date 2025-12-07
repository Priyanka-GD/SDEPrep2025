package discord.August26;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JumbledWords {
    public static void main(String args[]) throws IOException {
        int[] mapping = {8, 9, 4, 0, 2, 1, 3, 5, 7, 6};
        int[] nums = {991, 338, 38};
        int result[] = sortJumbled(mapping, nums);
        System.out.println("Result : " + result);
    }

    private static int[] sortJumbled(int[] mapping, int[] nums) {
        List<NumVal> list = new ArrayList<>();

        for (int num : nums) {
            int reconstructedNumber = reconstructNum(num, mapping);
            NumVal numVal = new NumVal(num);
            numVal.setMappedValue(reconstructedNumber);
            list.add(numVal);
        }
        Collections.sort(list);
        int result[] = new int[nums.length];
        int idx = 0;
        for (NumVal numVal : list)
            result[idx++] = numVal.number;

        return result;

    }

    private static int reconstructNum(int number, int[] mapping) {
        if (number == 0)
            return mapping[0];
        int reconstructedNumber = 0;
        int placeValue = 1;
        while (number > 0) {
            int digit = number % 10;
            // Reconstruct the number
            reconstructedNumber = reconstructedNumber + mapping[digit] * placeValue;
            placeValue *= 10;
            number /= 10;
        }
        return reconstructedNumber;
    }

}

class NumVal implements Comparable<NumVal> {
    int number;
    int mappedValue = 0;

    public NumVal(int num) {
        this.number = num;
    }

    public void setMappedValue(int num) {
        this.mappedValue = num;
    }

    @Override
    public int compareTo(NumVal other) {
        return this.mappedValue - other.mappedValue;
    }
}
