public class NumMatrix {
    // dp[i][j]表示(0,0)到(i-1,j-1)的和
    private int[][] dp;
    public NumMatrix(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        this.dp = new int[m+1][n+1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = dp[i-1][j] + dp[i][j-1] - dp[i-1][j-1] + matrix[i-1][j-1];
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        return dp[row2+1][col2+1] - dp[row2+1][col1] - dp[row1][col2+1] + dp[row1][col1];
    }

    public static void main(String[] args) {
        NumMatrix matrix = new NumMatrix(new int[][] {{3, 0, 1, 4, 2}, {5, 6, 3, 2, 1}, {1, 2, 0, 1, 5}, {4, 1, 0, 1, 7}, {1, 0, 3, 0, 5}});
        System.out.println(matrix.sumRegion(1, 1, 2, 2));
    }
}
