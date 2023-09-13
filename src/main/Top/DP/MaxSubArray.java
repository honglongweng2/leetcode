package Top.DP;

/**
 * No.53 最大子数组和
 *
 */
public class MaxSubArray {

    /**
     * 思路：dp[i]代表结尾为nums[i]的子数组最大和，最后遍历整个dp获取其中最大值。
     * dp[i]有两个选择，一是与前面的子数组合并，此时dp[i]=dp[i-1]+nums[i]；二是自己独立形成新的子数组，此时dp[i]=nums[i]
     */
    public int maxSubArrayOld(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0];
        for (int i = 1; i < n; i++) {
            dp[i] = Math.max(nums[i], dp[i-1] + nums[i]);
        }
        int result = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            result = Math.max(dp[i], result);
        }
        return result;
    }

    /**
     * 优化思路：因为dp[i]只跟dp[i-1]相关，可以简化dp为一个值currentMax
     */
    public int maxSubArray(int[] nums) {
        int max = Integer.MIN_VALUE, currentMax =0;
        for (int num : nums) {
            currentMax = Math.max(num, currentMax+num);
            max = Math.max(max, currentMax);
        }
        return max;
    }
}
