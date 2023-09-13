package Top.ArrayOrString;

public class Jump {

    /**
     * No.55 数组是当前位置可以jump的最远距离
     *
     * 思路：贪心，每个点能到达的最远距离是当前位置+可跳距离(nums[i]+i)
     */
    public boolean canJump(int[] nums) {
        int far = nums[0];
        for (int i = 1; i < nums.length - 1; i++) {
            if (far >= i) {
                // far >= i 表示之前能跳到i
                far = Math.max(far, nums[i] + i);
            }
        }
        return far >= nums.length - 1;
    }

    /**
     * No.45 最小步数跳到数组尾
     *
     * 思路：贪心，记录跳到当前位置所需的步数result，仅当上一步的最远距离current_far==i时，进入下一步并更新current_far。在下一步里仍按nums[i]+i
     */
    public int jump(int[] nums) {
        int result = 0, current_far = 0, far = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            far = Math.max(far, nums[i]+i);
            if (i == current_far) {
                result++;
                current_far = far;
            }
        }
        return result;
    }
}
