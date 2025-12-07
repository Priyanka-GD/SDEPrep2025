package neetcode75;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class PacificAtlantic {
    public List<List<Integer>> pacificAtlantic (int[][] heights) {

        int rows = heights.length;
        int cols = heights[0].length;

        Queue<int[]> pacificOceanGrids = new LinkedList<>();
        Queue<int[]> atlanticOceanGrids = new LinkedList<>();
        // Fill pacificOceanGrids with left side grids co-ordinates
        // Fill atlanticOceanGrids with right side grids co-ordinates
        for (int i = 0; i < rows; i++) {
            pacificOceanGrids.add(new int[]{i, 0});
            atlanticOceanGrids.add(new int[]{i, cols - 1});
        }

        // Fill pacificOceanGrids with upper side grids co-ordinates
        // Fill atlanticOceanGrids with lower side grids co-ordinates
        for (int i = 0; i < cols; i++) {
            pacificOceanGrids.add(new int[]{0, i});
            atlanticOceanGrids.add(new int[]{rows - 1, i});
        }

        boolean[][] reachablePacificGrids = bfs(pacificOceanGrids, rows, cols, heights);
        boolean[][] reachableAtlanticGrids = bfs(atlanticOceanGrids, rows, cols, heights);

        List<List<Integer>> commonGrids = new ArrayList<>();
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (reachablePacificGrids[row][col] && reachableAtlanticGrids[row][col]) {
                    commonGrids.add(List.of(row, col));
                }
            }
        }
        return commonGrids;
    }

    private boolean[][] bfs (Queue<int[]> grids, int rows, int cols, int[][] heights) {
        boolean[][] reachable = new boolean[rows][cols];
        int dirs[][] = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

        while (!grids.isEmpty()) {
            int cell[] = grids.poll();
            reachable[cell[0]][cell[1]] = true;
            for (int dir[] : dirs) {
                int newRow = cell[0] + dir[0];
                int newCol = cell[1] + dir[1];
                if (newRow < 0 || newRow >= rows || newCol < 0 || newCol >= cols || reachable[newRow][newCol]
                        || heights[newRow][newCol] < heights[cell[0]][cell[1]])
                    continue;
                grids.add(new int[]{newRow, newCol});
            }
        }
        return reachable;
    }

    public static void main (String args[]) throws IOException {
        int[][] heights = {
                {1, 2, 2, 3, 5},
                {3, 2, 3, 4, 4},
                {2, 4, 5, 3, 1},
                {6, 7, 1, 4, 5},
                {5, 1, 1, 2, 4}
        };
        System.out.println(new PacificAtlantic().pacificAtlantic(heights));
    }
}
