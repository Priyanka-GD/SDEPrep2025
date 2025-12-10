package amazoninternal.codingassessment;

import java.util.Arrays;

//Find rows with most 1s, each row is sorted
public class BinaryRow {
    public static void main(String args[]) {
        int[][] input = {{0, 1, 1, 1, 1}, {0, 0, 1, 1, 1}, {1, 1, 1, 1, 1}, {0, 0, 0, 0, 0}};
        System.out.println("Row with most 1s is: " + getRowWithMostOnes(input));
    }

    private static int getRowWithMostOnes(int[][] input) {
        int rows = input.length;
        int cols = input[0].length;
        int rowNo = 0;
        int maxCount = 0;

        for (int r = 0; r < rows; r++) {
            int countZeros = 0;
            for (int c = 0; c < cols; c++) {
                if (input[r][c] == 1) {
                    break;
                }
                countZeros++;
            }
            int ones = cols - countZeros;
            if (ones > maxCount) {
                maxCount = ones;
                rowNo = r;
            }
        }
        return rowNo;
    }
}
