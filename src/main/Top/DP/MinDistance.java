package Top.DP;

// No.72
public class MinDistance {
    // edit distance
    public int minDistanceEdit(String word1, String word2) {
        // 递归: 三种策略，insert是比较word1[i]和word2[j+1]，remove是比较word1[i+1]和word2[j]，replace是比较word1[i+1]和word2[j+1]，每次取三种策略的最小值+1
        // dp: 若word1[i],word2[j]相同，则dp[i][j] = dp[i-1][j-1]；反之则在[i-1][j-1], [i-1][j], [i][j-1]三者中取最小并+1。
        int m = word1.length(), n = word2.length();
        int[][] dp = new int[m+1][n+1];
        // 初始化边界
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }
        for (int i = 1; i <= n; i++) {
            dp[0][i] = i;
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i-1) == word2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1];
                } else {
                    dp[i][j] = Math.min(dp[i-1][j-1], Math.min(dp[i][j-1], dp[i-1][j])) + 1;
                }
            }
        }
        return dp[m][n];
    }

}
