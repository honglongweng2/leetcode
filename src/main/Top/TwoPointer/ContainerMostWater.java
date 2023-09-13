package Top.TwoPointer;

public class ContainerMostWater {
    /**
     * No.11 获取最大储水量
     *
     * 思路：双指针，从两侧往中间，每步计算容量。height低的一方动（贪心）
     */
    public int maxArea(int[] height) {
        int left=0, right=height.length-1, area=0;
        while (left < right) {
            area = Math.max(Math.min(height[left], height[right]) * (right - left), area);
            if (height[left] > height[right]) {
                right --;
            }else {
                left ++;
            }
        }
        return area;
    }
}
