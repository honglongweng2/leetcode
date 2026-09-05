package Top.DP;

import java.util.List;

public class MinTriangle {
    // No.120
    // 思路：从底往上遍历，dp[i]表示i位置的最小和
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[] dp = new int[n];
        // 初始化为最下层
        for (int i = 0; i < n; i++) {
            dp[i] = triangle.get(n-1).get(i);
        }
        for (int i = n-2; i >=0; i--) {
            List<Integer> cur = triangle.get(i);
            for (int j = 0; j < cur.size(); j++) {
                // 倒数第二层的dp[0]取 倒数第一层的0和1中的最小值 + 本身
                // dp[1]是取倒数第一层的1和2，所以更新掉dp[0]并不影响
                dp[j] = Math.min(dp[j], dp[j+1]) + cur.get(j);
            }
        }
        return dp[0];
    }

    public static void main(String[] args) {
        List<List<Integer>> triangle = List.of(
//                List.of(2),
//                List.of(3, 4),
//                List.of(6, 5, 7),
//                List.of(4, 1, 8, 3)
                List.of(-11)
        );
        MinTriangle so = new MinTriangle();
        System.out.println(so.minimumTotal(triangle));
    }
}
