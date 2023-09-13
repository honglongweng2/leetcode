package pre;

import java.util.*;
import java.util.stream.Collectors;

public class MarchForth {
    public static void main(String[] args) {
        int[][] array = new int[][] {{93,56,92},{53,44,18},{86,44,69},{54,60,30}};
        System.out.println(connectTwoGroups(Arrays.stream(array).map(l -> Arrays.stream(l).boxed().collect(Collectors.toList())).collect(Collectors.toList())));
    }
    public static int maxProductPath(int[][] grid) {
        long M = (long) (1e9+7);
        int m = grid.length, n = grid[0].length;
        long[][] dp_positive = new long[m][n];
        long[][] dp_negative = new long[m][n];
        dp_positive[0][0] = grid[0][0];
        dp_negative[0][0] = grid[0][0];
        for (int i = 1; i < m; i++) {
            // 初始化dp
            dp_positive[i][0] = dp_positive[i-1][0] * grid[i][0];
            dp_negative[i][0] = dp_negative[i-1][0] * grid[i][0];
        }
        for (int j = 1; j < n; j++) {
            // 初始化dp
            dp_positive[0][j] = dp_positive[0][j-1] * grid[0][j];
            dp_negative[0][j] = dp_negative[0][j-1] * grid[0][j];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (grid[i][j] >= 0) {
                    // move right vs move down
                    dp_positive[i][j] = Math.max(dp_positive[i][j - 1], dp_positive[i - 1][j]) * grid[i][j];
                    dp_negative[i][j] = Math.min(dp_negative[i][j - 1], dp_negative[i - 1][j]) * grid[i][j];
                } else {
                    dp_positive[i][j] = Math.min(dp_negative[i][j - 1], dp_negative[i - 1][j]) * grid[i][j];
                    dp_negative[i][j] = Math.max(dp_positive[i][j - 1], dp_positive[i - 1][j]) * grid[i][j];
                }
            }
        }
        return dp_positive[m-1][n-1] < 0 ?  -1 : (int) (dp_positive[m-1][n-1] % M);
    }

    public static int connectTwoGroups(List<List<Integer>> cost) {
        int m = cost.size(), n = cost.get(0).size();
        int result = 0;
//        if (m >= n) {
            // 个数多的group先挑边
            int[] visitedI = new int[m];
            int[] lenI = new int[m];
            int[] visitedJ = new int[n];
            for (int i = 0; i < m; i++) {
                int min = cost.get(i).get(0);
                int visited = 0;
                lenI[i] = min;
                for (int j = 1; j < n; j++) {
                    if (cost.get(i).get(j) < min) {
                        min = cost.get(i).get(j);
                        visited = j;
                        visitedI[i] = j;
                        lenI[i] = min;
                    }
                }
                result += min;
                visitedJ[visited]++;
            }
            for (int j = 0; j < n; j++) {
                // 遍历visited看groupJ没取到的点
                int min = cost.get(0).get(j);
                int visited = 0;
                if (visitedJ[j] == 0) {
                    for (int i = 1; i < m; i++) {
                        if (cost.get(i).get(j) < min) {
                            min = cost.get(i).get(j);
                            visited = i;
                        }
                    }
                    if (visitedJ[visitedI[visited]] > 1) {
                        result -= lenI[visited];
                        visitedJ[visitedI[visited]]--;
                    }
                    result += min;
                }
            }
//        } else {

//        }
        return result;
    }
}
