package discord;

public class TicTacToeWinner {
    public static void main (String args[]) {
        /*int[][] moves = {
                {0, 0},
                {2, 0},
                {1, 1},
                {2, 1},
                {2, 2}
        };*/
        int[][] moves = {
                {0, 0},
                {1, 1},
                {2, 0},
                {1, 0},
                {1, 2},
                {2, 1},
                {0, 1},
                {0, 2},
                {2, 2}};
        System.out.println(new TicTacToeWinner().tictactoe(moves));
    }

    public String tictactoe (int[][] moves) {
        boolean Aturn = true;
        char[][] board = new char[3][3];
        for (int move[] : moves) {
            if (Aturn) {
                board[move[0]][move[1]] = 'X';
                if (searchDiagonal(board, 'X') || searchRow(board, 'X', move[0]) || searchColumn(board, 'X', move[1]))
                    return "A";
            } else {
                board[move[0]][move[1]] = 'O';
                if (searchDiagonal(board, 'O') || searchRow(board, 'O', move[0]) || searchColumn(board, 'O', move[1]))
                    return "B";
            }
            Aturn = !Aturn;
        }
        if (checkPendingMoves(board))
            return "Pending";
        return "Draw";
    }

    private boolean searchDiagonal (char[][] board, char playerChar) {
        boolean diagonallySameFromTopLeft = false;
        boolean diagonallySameFromTopRight = false;
        boolean diagonallySameFromBottomRight = false;
        boolean diagonallySameFromBottomLeft = false;
        for (int row = 0; row < board.length; row++) {
            if (board[row][row] == playerChar)
                diagonallySameFromTopLeft = true;
            else {
                diagonallySameFromTopLeft = false;
                break;
            }
        }
        int col = 2;
        for (int row = 0; row < board.length; row++) {
            if (board[row][col] == playerChar)
                diagonallySameFromTopRight = true;
            else {
                diagonallySameFromTopRight = false;
                break;
            }
            col--;
        }
        col = 0;
        for (int row = board.length - 1; row >= 0; row--) {
            if (board[row][col] == playerChar)
                diagonallySameFromBottomLeft = true;
            else {
                diagonallySameFromBottomLeft = false;
                break;
            }
            col++;
        }
        col = 2;
        for (int row = board.length - 1; row >= 0; row--) {
            if (board[row][col] == playerChar)
                diagonallySameFromBottomRight = true;
            else {
                diagonallySameFromBottomRight = false;
                break;
            }
            col--;
        }
        return diagonallySameFromTopLeft || diagonallySameFromTopRight || diagonallySameFromBottomRight || diagonallySameFromBottomLeft;
    }

    private boolean searchRow (char[][] board, char playerChar, int row) {
        for (int col = 0; col < board[0].length; col++) {
            if (board[row][col] != playerChar)
                return false;
        }
        return true;
    }

    private boolean searchColumn (char[][] board, char playerChar, int col) {
        for (int row = 0; row < board.length; row++) {
            if (board[row][col] != playerChar)
                return false;
        }
        return true;
    }

    private boolean checkPendingMoves (char[][] board) {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                if (board[row][col] == '\u0000')
                    return true;
            }
        }
        return false;
    }
}
