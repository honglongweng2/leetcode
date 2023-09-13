package Top;

import java.util.HashMap;
import java.util.Map;

/**
 * 给出n个数字，求最多有多少个不重叠的非空区间，每个区间内eor都为0
 *
 * 思路：dp[i]表示在坐标i处最优划分下的解。
 * 两种情况：nums[i]不包含在eor=0的区间内，则dp[i]=dp[i-1]；
 * nums[i]包含在eor=0的区间内，则需要找到最近的坐标k，使eor(k+1->i)=0，此时dp[i] = max(dp[k]+1,dp[i-1])
 * 假设eor(0->i)=x，则eor(0->k)=x，记录eor的map以定位k。
 * 此处有一个假设，若k和i之间还有另一个l，使得eor(l+1->i)=0，则eor(k+1->l)=0，假设不成立（因为此时有两个最优划分，k+1->l,l+1->i）
 */
public class MostEor {
    public static void main(String[] args) {
        MostEor main = new MostEor();
        System.out.println(main.getMostEor(new int[] {0, 3, 2, 1, 4, 0, 1}));
    }

    public int getMostEor(int[] nums) {
        int[] dp = new int[nums.length];
        Map<Integer, Integer> lastEor = new HashMap<>();
        lastEor.put(0, -1);
        int eor = 0;
        for (int i = 0; i < nums.length; i++) {
            eor ^= nums[i];
            if (lastEor.containsKey(eor)) {
                // 命中map则表示存在坐标k，使eor(k->i)=0
                int pre = lastEor.get(eor);
                // 没有dp[-1]
                dp[i] = pre == -1 ? 1 :dp[pre] + 1;
            }
            if (i > 0) {
                dp[i] = Math.max(dp[i-1], dp[i]);
            }
            lastEor.put(eor, i);
        }
        return dp[nums.length-1];
    }
}
