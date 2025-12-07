package ziprecruiter;

import java.io.IOException;

public class CoordinatesCollide {
    public static void main(String args[]) throws IOException {
        int cods[][] = {{0, 1}, {1, 2}, {1, 4}};
        int count = collisionNumber(cods);
        System.out.println(count);

    }

    private static int collisionNumber(int[][] cods) {
        int count = 0;
        for (int idx = 0; idx < cods.length; idx++) {
            for (int idx1 = idx + 1; idx1 < cods.length; idx1++) {
                if (Math.abs(cods[idx1][0]) - cods[idx][0] <= 2 && Math.abs(cods[idx1][1]) - cods[idx][1] <= 2)
                    count++;
            }
        }
        return count;
    }

}
