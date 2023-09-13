package Top.Matrix;

public class LifeGame {

    public static void main(String[] args) {
        LifeGame main = new LifeGame();
        main.gameOfLife(new int[][] {
                {0,1,0},{0,0,1},{1,1,1},{0,0,0}
        });
    }
    /**
     * No.289
     * 规则1 1周围8格1个1则变0（无法繁衍）
     * 规则2 1周围2-3个1则不变
     * 规则3 1周围4-9个1则变0（超人口）
     * 规则4 0周围3个1则变0（重繁衍）
     *
     * 思路：不能新建矩阵，所以引入状态机：
     * 0：0->0 1: 1->1 2: 1->0 3: 0->1 计算完取余即可
     */
    public void gameOfLife(int[][] board) {
        int m = board.length, n = board[0].length;
        int[] dx = new int[] {-1, -1, -1, 0, 1, 1, 1, 0};
        int[] dy = new int[] {-1, 0, 1, 1, 1, 0, -1, -1};
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int count = 0;
                for (int k = 0; k < 8; k++) {
                    int x = i + dx[k], y = j + dy[k];
                    if (x >= 0 && x < m && y >= 0 && y < n && (board[x][y] == 1 || board[x][y] == 2)) {
                        // 计算的是原来的状态
                        count++;
                    }
                }
                if (board[i][j] == 1 && (count < 2 || count > 3)) {
                    board[i][j] = 2;
                }
                if (board[i][j] == 0 && count == 3) {
                    board[i][j] = 3;
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] %= 2;
            }
        }
    }

}
