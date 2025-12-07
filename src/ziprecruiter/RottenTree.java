package ziprecruiter;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class RottenTree {
    public static void main(String args[]) throws IOException {
        /*String[][] orchard = {
                {"T", "R", "T", "T"},
                {"R", "T", "T", "R"},
                {"T", "T", "T", "T"}
        };*/
        String[][] orchard = {
                {"T", "-", "T", "R"},
                {"-", "T", "-", "-"},
                {"T", "-", "T", "-"}
        };
        int days = 2;

        // Simulate the orchard structure after a given number of days
        orchardStructureAfterDays(orchard, days);

        // Print the final orchard structure
        for (String[] row : orchard)
            System.out.println(Arrays.asList(row));
    }

    private static void orchardStructureAfterDays(String[][] orchard, int days) {
        int rows = orchard.length;
        int cols = orchard[0].length;
        Queue<int[]> q = new LinkedList<>();

        // Add all initially rotten trees to the queue
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (orchard[r][c].equals("R")) {
                    q.add(new int[]{r, c, 0});  // Add rotten tree with day 0
                }
            }
        }

        // BFS to spread the rot to adjacent healthy trees
        int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        while (!q.isEmpty()) {
            int[] pair = q.poll();
            int row = pair[0];
            int col = pair[1];
            int day = pair[2];

            // Stop if the number of days is reached
            if (day == days) {
                return;
            }

            // Spread rot to adjacent healthy trees (T)
            for (int[] dir : dirs) {
                int newRow = row + dir[0];
                int newCol = col + dir[1];

                // Check bounds and if the tree is healthy (T), turn it rotten (R)
                if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols && orchard[newRow][newCol].equals("T")) {
                    orchard[newRow][newCol] = "R";
                    q.add(new int[]{newRow, newCol, day + 1});  // Add the newly rotten tree with the next day
                }
            }
        }
    }
}
