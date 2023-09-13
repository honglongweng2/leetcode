package Top.ArrayOrString;

/**
 * No.42 收集雨水，两侧高中间低则可收集，返回总大小
 *
 * 思路：双指针，往中间找低洼
 * 由left和right中较低的一方开始走，因为高的一方肯定可以作为墙壁接水
 * left和right移动时，相邻height的差值就是当前的接水数，直到遇到比最边缘墙壁(c)还高的墙壁
 */
public class TrapRain {
    public int trap(int[] height) {
        int result = 0, left = 0, right = height.length - 1;
        while (left < right) {
            if (height[left] <= height[right]) {
                int c = height[left];
                left++;
                while (height[left] < c) {
                    result += c - height[left];
                    left++;
                }
            } else {
                int c = height[right];
                right--;
                while (height[right] < c) {
                    result += c - height[right];
                    right--;
                }
            }
        }
        return result;
    }
}
