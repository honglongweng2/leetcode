package Top.Matrix;

public class ValidSudoku {
    /**
     * No.36 有效数独
     *
     * 思路：三个二维数组分别代表行/列/9宫格内是否有数字i
     */
    public boolean isValidSudoku(char[][] board) {
        boolean[][] row = new boolean[9][9];
        boolean[][] col = new boolean[9][9];
        boolean[][] cell = new boolean[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') continue;
                int c = board[i][j] - '1';
                if (row[i][c] || col[c][j] || cell[i/3*3+j/3][c]) return false;
                row[i][c] = col[c][j] = cell[i/3*3+j/3][c] = true;
            }
        }
        return true;
    }
}
