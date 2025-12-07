package neetcode75;

import java.io.IOException;

public class SearchWord {
    public static boolean exist(char[][] board, String word) {
        int rows = board.length;
        int cols = board[0].length;
        boolean[][] visited = new boolean[rows][cols];

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (searchWord(0, row, col, board, word, visited))
                    return true;
            }
        }
        return false;
    }

    private static boolean searchWord(int idx, int row, int col, char[][] board, String word, boolean[][] visited) {
        if (idx == word.length())
            return true;
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length
                || visited[row][col] || word.charAt(idx) != board[row][col])
            return false;
        visited[row][col] = true;
        if (searchWord(idx + 1, row - 1, col, board, word, visited) ||
                searchWord(idx + 1, row, col + 1, board, word, visited) ||
                searchWord(idx + 1, row + 1, col, board, word, visited) ||
                searchWord(idx + 1, row, col - 1, board, word, visited))
            return true;
        visited[row][col] = false;
        return false;
    }

    public static void main(String args[]) throws IOException {
        /*char[][] board = {
                {'A', 'B', 'C', 'D'},
                {'S', 'A', 'A', 'T'},
                {'A', 'C', 'A', 'E'}
        };

        // Initialize the word to be searched
        String word = "CAT"; */
        char[][] board = {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };

        // Define the word to search for
        String word = "ABCB";
        System.out.println(exist(board, word));
    }

}
