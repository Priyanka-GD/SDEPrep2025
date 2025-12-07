package neetcode75;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class NumIslands {
    public int numIslands(char[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        boolean[][] visited = new boolean[rows][cols];
        int numberOfIslands = 0;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (grid[row][col] == '1' && !visited[row][col]) {
                    bfs(grid, row, col, visited);
                    numberOfIslands++;
                }
            }
        }
        return numberOfIslands;
    }

    private void bfs(char[][] grid, int row, int col, boolean[][] visited) {
        int dirs[][] = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] { row, col });
        visited[row][col] = true;

        while (!queue.isEmpty()) {
            int cell[] = queue.poll();
            for (int dir[] : dirs) {
                int newRow = cell[0] + dir[0];
                int newCol = cell[1] + dir[1];
                if (newRow < 0 || newRow >= grid.length || newCol < 0 || newCol >= grid[0].length
                        || visited[newRow][newCol]
                        || grid[newRow][newCol] == '0')
                    continue;
                visited[newRow][newCol] = true;
                queue.add(new int[] { newRow, newCol });
            }
        }
    }
    public static void main (String args[]) throws IOException {
        char[][] grid = {
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}
        };
        System.out.println(new NumIslands().numIslands(grid));
    }
}
