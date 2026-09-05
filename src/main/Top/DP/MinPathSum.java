package Top.DP;

// No.64
public class MinPathSum {
    // 用grid当中dp，grid[i][j]记录到达i,j的最短路径，grid[i][j] = 上和左的最小值+本身
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        // 初始化边界
        for (int i = 1; i < m; i++) {
            grid[i][0] += grid[i-1][0];
        }
        for (int i = 1; i < n; i++) {
            grid[0][i] += grid[0][i-1];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                grid[i][j] += Math.min(grid[i-1][j], grid[i][j-1]);
            }
        }
        return grid[m-1][n-1];
    }

    public static void main(String[] args) {

    }
}
