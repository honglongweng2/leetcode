package Top.DP;

public class MaxSquare {
    // 思路：dp[i][j]表示以matrix[i][j]为边界的最大正方形大小
    // dp[i][j] = 0 if matrix[i][j] == 0
    // 如果[i,j]要扩大一格，必须[i-1,j-1] [i-1,j] [i,j-1]都是正方形都边界，
    public int maximalSquare(char[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int result = 0;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '0') {
                    dp[i][j] = 0;
                } else {
                    // 初始化边界
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = Math.min(dp[i - 1][j], Math.min(dp[i][j - 1], dp[i - 1][j - 1])) + 1;
                    }
                }
                result = Math.max(result, dp[i][j] * dp[i][j]);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        char[][] matrix = {{'0','1'},{'1','0'}};
        MaxSquare so = new MaxSquare();
        System.out.println(so.maximalSquare(matrix));
    }
}
