package Top;

/**
 * 求最长回文串，O(n)
 *
 * 思路：dp[i]表示到i位置最大回文串长度，R表示目前的回文串边界
 * 情况1，i>R，说明回文串还在扩
 * 情况2，i'（i相对于回文中心c的对称点）的回文区域在L（R相对于回文中心c的对称点）内，则i的回文区域也在R内，dp[i]=dp[i']
 * 情况3，i'（i相对于回文中心c的对称点）的回文区域在L（R相对于回文中心c的对称点）外，则i的回文区域截止R，dp[i]=R-i
 * 情况4，i'(i相对于回文中心c的对称点）的回文区域正好位于L(R相对于回文中心c的对称点），则i的回文区域还可以扩，之间从i+1的位置开始向外扩即可
 */
public class Manacher {
    public static void main(String[] args) {
        Manacher main = new Manacher();
        System.out.println(main.maxLcpsLength("abbacabba"));
    }

    public char[] manacherString(String str) {
        // 处理串，char之间插入特殊字符，不然无法处理偶数回文串
        char[] charArray = str.toCharArray();
        char[] result = new char[str.length()*2+1];
        int index = 0;
        for (int i = 0; i < result.length; i++) {
            // 偶数位特殊字符
            result[i] = ((i&1) == 0) ? '#' : charArray[index++];
        }
        return result;
    }

    public int maxLcpsLength(String s) {
        if (s == null || s.length() == 0) return 0;
        char[] str = manacherString(s);
        // 回文半径数组
        int[] dp = new int[str.length];
        // 回文右边界，回文串从L->R-1
        int R = -1;
        // 回文中心c
        int C = -1;
        // dp中的最大值
        int max = 0;
        for (int i = 0; i != str.length; i++) {
            // i至少的回文区域，先给dp
            // R>i对应情况2，3，4，此时瓶颈在i'的回文半径和i距离R的距离的最小值；R<i对应情况1，至少为1
            dp[i] = R > i ? Math.min(dp[2*C-i], R-i) : 1;
            // 尝试往外扩，其中情况2，3时等于扩失败
            while (i + dp[i] < str.length && i - dp[i] > -1) {
                if (str[i+dp[i]] == str[i-dp[i]]) {
                    dp[i]++;
                } else {
                    break;
                }
            }
            if (i + dp[i] > R) {
                R = i + dp[i];
                C = i;
            }
            max = Math.max(max, dp[i]);
        }
        // 由于结果值为回文串长度，而dp统计的是处理串的半径。
        // 奇数串时，dp=6回文串为#c#b#a#b#c#，max=5
        // 偶数串时，dp=5回文串为#b#a#a#b#，max=4
        return max - 1;
    }
}
