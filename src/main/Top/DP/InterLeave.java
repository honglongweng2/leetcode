package Top.DP;

// No.97
public class InterLeave {
    // 思路：dp[i][j]表示[i,j] s1[0,i-1]+s2[0,j-1]能否组合s3[0,i+j-1]
    // s3[i+j-1]可能来自于s1[i-1]也可能来自于s2[j-1]
    // dp[i][j] = (dp[i-1][j] && s1[i-1] == s3[i+j-1]) || (dp[i][j-1] && s2[j-1] == s3[i+j-1])
    // 空间优化：由于一个是上一行，一个是前一个值，压缩成一维数组
    // dp[j] = dp[j] && s1[i-1] == s3[i+j-1] || dp[j-1] && s2[j-1] == s3[i+j-1]
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }
        boolean[] dp = new boolean[s2.length()+1];
        dp[0] = true;
        for (int i = 0; i < s1.length()+1; i++) {
            for (int j = 0; j < s2.length()+1; j++) {
                if (i > 0) {
                    // 拆两步做，先取上一行dp[j]更新当前dp[j]
                    dp[j] = dp[j] && s1.charAt(i-1) == s3.charAt(i+j-1);
                }
                if (j > 0) {
                    // 再取前一个值，更新当前dp[j]
                    dp[j] = dp[j-1] && s2.charAt(j-1) == s3.charAt(i+j-1) || dp[j];
                }
            }
        }
        return dp[s2.length()];
    }

    public static void main(String[] args) {
        String s1 = "aabcc";
        String s2 = "dbbca";
        String s3 = "aadbbcbcac";
        InterLeave so = new InterLeave();
        System.out.println(so.isInterleave(s1, s2, s3));
    }
}
