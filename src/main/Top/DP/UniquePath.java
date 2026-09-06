package Top.DP;

// No.63
public class UniquePath {
    // 原来的grid当dp，把grid[i][j]代表有多少条路到达[i,j]，等于grid[i-1][j]+grid[i][j-1]
    // 障碍物为1，需要替换成0，这样不影响加法逻辑
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        if (obstacleGrid[0][0] == 1) return 0;
        // 初始化边界
        obstacleGrid[0][0] = 1;
        for (int i = 1; i < m; i++) {
            if (obstacleGrid[i][0] == 1 || obstacleGrid[i-1][0] == 0) {
                obstacleGrid[i][0] = 0;
            } else {
                obstacleGrid[i][0] = 1;
            }
        }
        for (int i = 1; i < n; i++) {
            if (obstacleGrid[0][i] == 1 || obstacleGrid[0][i-1] == 0){
                obstacleGrid[0][i] = 0;
            } else {
                obstacleGrid[0][i] = 1;
            }
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    obstacleGrid[i][j] = 0;
                } else {
                    obstacleGrid[i][j] = obstacleGrid[i-1][j]+obstacleGrid[i][j-1];
                }
            }
        }
        return obstacleGrid[m-1][n-1];
    }

    public static void main(String[] args) {
        int[][] obstacleGrid = {{0,0,0},{0,1,0},{0,0,0}};
        UniquePath so = new UniquePath();
        System.out.println(so.uniquePathsWithObstacles(obstacleGrid));
    }
}
