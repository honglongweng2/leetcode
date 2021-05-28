public class Test {
    private int n;
    // 存各个string之间的距离，如g[1][2]代表a[1]接a[2]时，a[2]需要加的长度
    private int[][] g;
    private String[] a;
    private int bestLen;
    private int[] path;
    private int[] bestPath;

    private void dfs(int d, int used, int curLen) {
        // 关于used, 假设n=5, 此时可以用二进制11111来表示各个string是否被用到，比如10011（19）代表1，2，5用了。
        // &操作=0即当前i没被使用
        if (curLen >= bestLen) return;
        if (d == n) {
            bestLen = curLen;
            bestPath = path.clone();
            return;
        }

        for (int i = 0; i < n; ++i) {
            if ((used & (1 << i)) != 0) continue;
            path[d] = i;
            dfs(d + 1,
                    used | (1 << i),
                    d == 0 ? a[i].length() : curLen + g[path[d - 1]][i]);
        }
    }

    public String shortestSuperstring(String[] A) {
        n = A.length;
        g = new int[n][n];
        a = A;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                g[i][j] = A[j].length();
                for (int k = 1; k <= Math.min(A[i].length(), A[j].length()); ++k) {
                    if (A[i].substring(A[i].length() - k).equals(A[j].substring(0, k))) {
                        // 后头对前尾，两次能重复就减k
                        g[i][j] = A[j].length() - k;
                    }
                }
            }
        }

        path = new int[n];
        bestLen = Integer.MAX_VALUE;

        dfs(0, 0, 0);

        StringBuilder ans = new StringBuilder(A[bestPath[0]]);
        for (int k = 1; k < n; ++k) {
            int i = bestPath[k - 1];
            int j = bestPath[k];
            ans.append(A[j].substring(A[j].length() - g[i][j]));
        }
        return ans.toString();
    }

    public static void main(String[] args) {
        Test test = new Test();
        System.out.println(test.shortestSuperstring(new String[] {"catg","ctaagt","gcta","ttca","atgcatc"}));
    }
}
