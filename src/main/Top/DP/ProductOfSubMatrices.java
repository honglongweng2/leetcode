package Top.DP;

public class ProductOfSubMatrices {
    public static void main(String[] args) {
        int[][] result = new int[][] {
                {7, 0, 2},
                {1, 2, 2}
        };
        System.out.println(prodSubMat(result, 2));
    }

    public static int prodSubMat(int[][] matA, int maxK)
    {
        int answer = 0;
        int m = matA.length, n = matA[0].length;
        // dp[i][j] represent the product of all elements that contains matA[i][j]
        // dp可以优化为一维数组，因为只跟自己左边和上边的值有关
        int[] dp = new int[n];
        dp[0] = matA[0][0];
        if (dp[0] <= maxK) answer++;
        for (int j = 1; j < n; j++) {
            dp[j] = dp[j-1] * matA[0][j];
            if (dp[j] <= maxK) answer++;
        }
        for (int i = 1; i < m; i++) {
            int productOfLine = 1;
            for (int j = 0; j < n; j++) {
                // 需要判0
                if (dp[i - 1] == 0) {
                    // 0右边所有乘积只能是0
                    answer += n - j;
                    break;
                }
                if (matA[i][j] == 0) {
                    // 当前为0时，dp[j] = 0且右边所有乘积都只能为0
                    dp[j] = 0;
                    answer += n - j;
                    break;
                }
                productOfLine *= matA[i][j];
                dp[j] = dp[j] * productOfLine;
                if (dp[j] <= maxK) answer++;
            }
        }

        return answer;
    }
}
