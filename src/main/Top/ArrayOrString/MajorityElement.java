package Top.ArrayOrString;

/**
 * No.169 求占数组n/2以上的数字（假设始终存在），空间复杂度要求O(1)
 *
 * 思路：摩尔投票。只要存在众数，让不同的两数一一抵消，最后能剩下的一个或几个数一定是众数。
 */
public class MajorityElement {
    public static void main(String[] args) {
        MajorityElement main = new MajorityElement();
        System.out.println(main.majorityElement(new int[] {1, 2, 1, 2, 1, 4}));
    }
    public int majorityElement(int[] nums) {
        if (nums.length == 1) return nums[0];
        int selectNum = 0;
        int count = 0;
        for (int num : nums) {
            if (count == 0) {
                // 上一轮选举失败，当前数成为候选人
                selectNum = num;
                count++;
            } else if (num == selectNum) {
                count++;
            } else {
                count--;
            }
        }
        return selectNum;
    }
}
